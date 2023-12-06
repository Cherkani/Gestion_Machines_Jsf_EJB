package Controllers;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import Entities.Login;
import Services.LoginFacade;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.RequestContext;
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginController {

    private String username;
    private String password;

 @EJB  // Use @EJB for EJB injection
    private LoginFacade loginService;
    // Add a setter for LoginService
    public void setLoginService(LoginFacade loginService) {
        this.loginService = loginService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

public String login() {
    if (isValidCredentials(username, password)) {
        // If login is successful, navigate to the desired page
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("username", username);
        return "/employe/List?faces-redirect=true";
    } else {
        // Display an error message on failure
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));

        // Show the pop-up message
        RequestContext.getCurrentInstance().execute("PF('invalidCredentialsDialog').show();");

        return null;
    }
}



    public String logout() {
        // Perform logout actions if needed
        return "/index.xhtml";
    }

  private boolean isValidCredentials(String username, String password) {
    try {
        List<Login> logins = loginService.findByUsernameAndPassword(username, password);
        return !logins.isEmpty();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


}
