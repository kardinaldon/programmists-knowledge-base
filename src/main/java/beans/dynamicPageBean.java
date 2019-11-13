package beans;

import lombok.Data;

import javax.inject.Named;

@Named
@Data
public class dynamicPageBean {

    private int pageId;
    private String pageUrl;

    private void urlHandler(int pageId) {


    }
}
