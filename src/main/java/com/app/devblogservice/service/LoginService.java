package com.app.devblogservice.service;

import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.exception.InternalServiceException;
import com.app.devblogservice.exception.LoginFailedException;

import com.app.devblogservice.model.Author;
import com.app.devblogservice.model.LoginDetails;
import com.app.devblogservice.repository.AuthorRepository;
import com.app.devblogservice.util.Constants;
import com.app.devblogservice.util.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final AuthorRepository authorRepository;

    @Autowired
    public LoginService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author verifyLoginDetails(LoginDetails loginDetails) throws BadRequestException,
            LoginFailedException {
        checkLoginDetails(loginDetails);
        Author user = authorRepository.findByAuthorIdAndPassword(loginDetails.getUsername(),
                loginDetails.getPassword());
        Optional.ofNullable(user).
                orElseThrow(() -> new LoginFailedException(ErrorMessage.USER_NOT_FOUND))
                .setPassword(null);
        return user;
    }

    public void checkLoginDetails(LoginDetails loginDetails) throws BadRequestException {
        if(null == loginDetails || null == loginDetails.getPassword() ||
        null == loginDetails.getUsername()){
            throw new BadRequestException(ErrorMessage.INVALID_DETAILS);
        }

        if(loginDetails.getUsername().isBlank() || loginDetails.getPassword().isBlank()){
            throw new BadRequestException(ErrorMessage.INVALID_DETAILS);
        }
    }
}
