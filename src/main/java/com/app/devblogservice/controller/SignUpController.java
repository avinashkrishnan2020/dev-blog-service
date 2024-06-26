package com.app.devblogservice.controller;

import com.app.devblogservice.exception.AuthorExistsException;
import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.model.Author;
import com.app.devblogservice.service.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    @Operation(summary = "API to sign up a new user/author")
    public ResponseEntity<Author> signUpNewUser(@RequestBody Author userDetails) throws AuthorExistsException, BadRequestException {
        return new ResponseEntity<>(signUpService.signUpAuthor(userDetails), HttpStatus.OK);
    }
}
