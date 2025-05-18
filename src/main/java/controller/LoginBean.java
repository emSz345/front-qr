package main.java.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;


/**
 *
 * @author emmso
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;

    public String login() {
        if ("professor".equals(username) && "123".equals(password)) {
            return "qr.xhtml?faces-redirect=true";
        }
        return null;
    }

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
