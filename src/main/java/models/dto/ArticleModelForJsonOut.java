package models.dto;

import lombok.Data;

@Data
public class ArticleModelForJsonOut {

    private int articleId;

    private String title;

    private String smallDescription;

    private String dateOfCreation;

    private String description;

    private String categoryName;

    private String userName;


}
