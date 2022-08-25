package com.victor.noloosecoins.models.user.forms;

import com.victor.noloosecoins.models.user.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {


    @NotBlank(message = "name can't be blank")
    private String name;
    @Email(message = "email is in a not valid format")
    private String email;
    @Length(min = 8, max = 60, message = "password must have at lest 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "password must have at least one upper case, one lower case, one number, one of the special characters '@#$%^&+=' ")
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

    public User convertToUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        return user;
    }
}
