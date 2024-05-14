package com.app.devblogservice.service;

import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.exception.InternalServiceException;
import com.app.devblogservice.exception.UnregisteredUserException;
import com.app.devblogservice.model.Author;
import com.app.devblogservice.model.LoginDetails;
import com.app.devblogservice.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthorRepository authorRepository;

    @Autowired
    public LoginService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author verifyLoginDetails(LoginDetails loginDetails) throws BadRequestException, UnregisteredUserException, InternalServiceException {
        checkLoginDetails(loginDetails);
        try {
            Author user = authorRepository.findByAuthorIdAndPassword(loginDetails.getUsername(),
                    loginDetails.getPassword());
            user.setPassword(null);
            return user;
        } catch(EntityNotFoundException ex){
            throw new UnregisteredUserException();
        } catch(Exception ex){
            throw new InternalServiceException();
        }
    }

    public void checkLoginDetails(LoginDetails loginDetails) throws BadRequestException {
        if(null == loginDetails || null == loginDetails.getPassword() ||
        null == loginDetails.getUsername()){
            throw new BadRequestException("Bad Login Details");
        }

        if(loginDetails.getUsername().isBlank() || loginDetails.getPassword().isBlank()){
            throw new BadRequestException("Bad Login Details");
        }
    }
}
