package beans.menu_bean;

import org.primefaces.model.menu.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;


@Named
@RequestScoped
public class MenuView implements Serializable {

    private static final long serialVersionUID = -195629365350075147L;
    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu();
            firstSubmenu.setLabel("Dynamic Submenu");

        DefaultMenuItem item = new DefaultMenuItem();
            item.setValue("External");
            item.setUrl("http://www.primefaces.org");
            item.setIcon("pi pi-home");
        firstSubmenu.getElements().add(item);
        model.getElements().add(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu();
            secondSubmenu.setLabel("Dynamic Actions");

            item = new DefaultMenuItem();
            item.setValue("Save");
            item.setIcon("pi pi-save");
            item.setCommand("#{menuView.save}");
            item.setUpdate("messages");

            secondSubmenu.getElements().add(item);
        model.getElements().add(firstSubmenu);

            item = new DefaultMenuItem();
            item.setValue("Delete");
            item.setIcon("pi pi-times");
            item.setCommand("#{menuView.delete}");
            item.setAjax(false);

        secondSubmenu.getElements().add(item);


            item = new DefaultMenuItem();
            item.setValue("Redirect");
            item.setIcon("pi pi-search");
            item.setCommand("#{menuView.redirect}");
        secondSubmenu.getElements().add(item);

        model.getElements().add(secondSubmenu);

    }

    public MenuModel getModel() {
        return model;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
