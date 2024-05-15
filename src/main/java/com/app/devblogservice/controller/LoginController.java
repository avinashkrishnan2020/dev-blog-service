package com.app.devblogservice.controller;

import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.exception.InternalServiceException;
import com.app.devblogservice.exception.LoginFailedException;
import com.app.devblogservice.model.Author;
import com.app.devblogservice.model.LoginDetails;
import com.app.devblogservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Author> loginUser(@RequestBody LoginDetails loginDetails) throws
            BadRequestException, LoginFailedException {
        return new ResponseEntity<>(this.loginService.verifyLoginDetails(loginDetails),
                HttpStatus.OK);
    }

}
