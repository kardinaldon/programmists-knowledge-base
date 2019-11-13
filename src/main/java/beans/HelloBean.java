package beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@Data
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getSayWelcome(String name){
        //check if null?
        if("".equals(name) || name ==null){
            return "";
        }
        else if(name.equals("1")) {
            return "/administrator/secured/template/article_templates/list_of_article_template.xhtml";
        }else if (name.equals("2")){
            return "/administrator/secured/template/article_templates/create_article.xhtml";
        } else {
            return "/";
        }
    }
}
