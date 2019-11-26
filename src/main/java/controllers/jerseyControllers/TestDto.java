package controllers.jerseyControllers;

import lombok.Data;

import java.util.List;

@Data
public class TestDto {
    private int categoryId;
    private String categoryTitle;
    private String parentId;
    List<TestDto> testDtoList;
}
