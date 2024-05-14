package com.app.devblogservice.controller;

import com.app.devblogservice.model.LoginDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    public LoginController(){

    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDetails loginDetails){
        return null;
    }

}
