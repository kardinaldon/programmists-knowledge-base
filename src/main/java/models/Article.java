package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    public Article() {
    }

    public Article(String title, String description, Category category, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public int getId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
