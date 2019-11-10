package beans;

import com.sun.net.httpserver.HttpServer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
@Data
@Slf4j
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 4441192489298102578L;


}
