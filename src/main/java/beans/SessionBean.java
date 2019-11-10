package beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


@ManagedBean
@SessionScoped
@Slf4j
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 4441192489298102578L;

    @Getter
    @Setter
    private String sessionId;




}
