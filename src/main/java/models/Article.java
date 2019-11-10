package models;

import lombok.*;
import models.user.User;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Indexed
@AnalyzerDef(name = "articleAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class),
                @TokenFilterDef(factory = NGramFilterFactory.class,
                        params = {
                                @Parameter(name = "minGramSize", value = "3"),
                                @Parameter(name = "maxGramSize", value = "300")
                        })
        })
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="articleId", updatable = false, nullable = false)
    private int articleId;

    @NaturalId
    @Field(index = Index.YES, store = Store.NO)
    @Analyzer(definition = "articleAnalyzer")
    @Column(name="title")
    private String title;

    @Column(name="smallDescription")
    @Field(index = Index.YES, store = Store.NO)
    @Analyzer(definition = "articleAnalyzer")
    private String smallDescription;

    @Column(name="dateOfCreation")
    private LocalDateTime dateOfCreation;

    @Column(name="description")
    @Field(index = Index.YES, store = Store.NO)
    @Analyzer(definition = "articleAnalyzer")
    private String description;

    @ManyToOne (optional=false, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name="category")
    private Category category;

    @ManyToOne (optional=false, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn (name="creator")
    private User user;

}
