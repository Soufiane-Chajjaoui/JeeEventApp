package com.example.demo.beans;

import com.example.demo.entities.User;
import com.example.demo.services.AuthService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class UserMB implements Serializable {
    @EJB
    private AuthService authService;

    public UserMB() {
    }

    public boolean isExistsUserWithEmail(String email) {
        User user = authService.findByEmail(email);
        return user != null;
    }
}
