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
        HttpSession session = request.getSession();

        if (lb.validar(tmp_email, tmp_password)) {
            String name = lb.getName(tmp_email, tmp_password);
            session.setAttribute("username", name);
            return "index";
        } else {
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario y/o contraseña inválidos.", null);
            fc.addMessage(null, fm);
            return "login";
        }
    }

    public String logout() {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return "login.xhtml";
    }

}
