package com.victor.noloosecoins.models.user.forms;

import com.victor.noloosecoins.models.user.User;

public class UserForm {


    private String name;
    private String email;
    private String password;

    public UserForm() {
    }

    public UserForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public User getUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        return user;
    }
}
