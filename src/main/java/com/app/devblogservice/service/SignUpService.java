package com.app.devblogservice.service;

import com.app.devblogservice.exception.AuthorIdExistsException;
import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.exception.DatabaseConnectivityException;
import com.app.devblogservice.model.Author;
import com.app.devblogservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final AuthorRepository authorRepository;

    @Autowired
    public SignUpService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author signUpAuthor(Author signUpDetails) throws BadRequestException, AuthorIdExistsException, DatabaseConnectivityException {
        checkSignUpDetails(signUpDetails);
        try{
            Author author = this.authorRepository.save(signUpDetails);
            author.setPassword(null);
            return author;
        }catch(DataIntegrityViolationException ex){
            throw new AuthorIdExistsException();
        } catch(Exception ex) {
            throw new DatabaseConnectivityException();
        }
    }

    public void checkSignUpDetails(Author signUpDetails) throws BadRequestException {
        if(null == signUpDetails ||
                null == signUpDetails.getLastName() || null == signUpDetails.getFirstName()
            || null == signUpDetails.getPassword() || null == signUpDetails.getAuthorId()){
            throw new BadRequestException("Bad Sign Up details");
        }

        if(signUpDetails.getAuthorId().isBlank() || signUpDetails.getPassword().isBlank()
        || signUpDetails.getFirstName().isBlank() || signUpDetails.getLastName().isBlank()){
            throw new BadRequestException("Bad Sign Up details");
        }

    }

}
