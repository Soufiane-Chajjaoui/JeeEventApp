package com.example.demo.BeanManager;

import com.example.demo.Utils.SessionManager;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.AuthService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@SessionScoped
@Named
public class AuthBean implements Serializable {

    @EJB
    private AuthService authService;

    private User userRegistration;

    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    public void init() {
        this.userRegistration = new User();
    }

    public AuthBean() {
    }

    public User getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(User userRegistration) {
        this.userRegistration = userRegistration;
    }

    public String register() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (this.userRegistration.getEmail().length() > 12) {
            if (this.authService.findByEmail(this.userRegistration.getEmail()) == null) {
                this.authService.register(this.userRegistration);
                HttpSession session = SessionManager.getSession();
                session.setAttribute("logged", this.userRegistration);
                return "index";
            } else {
                facesContext.addMessage("registerForm:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your Email Already Registered", null));
                facesContext.getExternalContext().getFlash().setKeepMessages(true);
            }
        }
        return "register";
    }

    public String login() {
        if ((this.userRegistration = this.authService.login(this.email, this.password)) != null) {

            HttpSession session = SessionManager.getSession();
            session.setAttribute("logged", this.userRegistration);
            if (isAdmin())
                return "home";
            return "index";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("loginForm:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication is Failed", null));
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "login";
    }

    public boolean isAdmin() {
        return this.userRegistration != null && this.userRegistration.getRole() == Role.ADMIN;
    }


    public String logout() {
        HttpSession session = SessionManager.getSession();
        session.invalidate();
        this.userRegistration = null;
        return "login";
    }
}