package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="articleId")
    private int articleId;

    @Column(name="title")
    private String title;

    @Column(name="smallDescription")
    private String smallDescription;

    @Column(name="dateOfCreation")
    private LocalDateTime dateOfCreation;

    @Column(name="description")
    private String description;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="category")
    private Category category;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="creator")
    private User user;

}
