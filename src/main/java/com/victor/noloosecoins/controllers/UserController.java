package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.user.User;
import com.victor.noloosecoins.models.user.dtos.UserDto;
import com.victor.noloosecoins.models.user.forms.UserForm;
import com.victor.noloosecoins.repositories.UserRepository;
import com.victor.noloosecoins.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody @Valid UserForm userForm){
        UserDto user = service.registerNewUser(userForm);
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
