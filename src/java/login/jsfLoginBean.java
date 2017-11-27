package login;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class jsfLoginBean implements Serializable {

    private final HttpServletRequest request;
    private final FacesContext fc;
    private FacesMessage fm;

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public jsfLoginBean() {
        fc = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
    }

    public String validate() {
        
        String tmp_email = this.getEmail();
        String tmp_password = this.getPassword();
        
        LoginBean lb = new LoginBean();
        //HttpSession session = request.getSession();

        if (lb.validar(tmp_email, tmp_password)) {
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login exitoso", null);
            fc.addMessage(null, fm);
            return "login";
        } else{
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login FALLIDO", null);
            fc.addMessage(null, fm);
            return "login";
        }
    }

}
