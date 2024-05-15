package com.app.devblogservice.service;

import com.app.devblogservice.exception.AuthorExistsException;
import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.model.Author;
import com.app.devblogservice.repository.AuthorRepository;
import com.app.devblogservice.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignUpService {

    private final AuthorRepository authorRepository;

    @Autowired
    public SignUpService(AuthorRepository authorRepository){

        this.authorRepository = authorRepository;
    }

    public Author signUpAuthor(Author signUpDetails) throws AuthorExistsException, BadRequestException {
        checkSignUpDetails(signUpDetails);
        checkAuthorExists(signUpDetails);
        Author author = authorRepository.saveAndFlush(signUpDetails);
        author.setPassword(null);
        return author;
    }

    public void checkAuthorExists(Author signUpDetails) throws AuthorExistsException {
        Author author = this.authorRepository.findByAuthorIdAndPassword(signUpDetails.getAuthorId(), signUpDetails.getPassword());
        if(null != author) {
            throw new AuthorExistsException();
        }
    }
    public void checkSignUpDetails(Author signUpDetails) throws BadRequestException {
        if(null == signUpDetails ||
                null == signUpDetails.getLastName() || null == signUpDetails.getFirstName()
            || null == signUpDetails.getPassword() || null == signUpDetails.getAuthorId()){
            throw new BadRequestException(ErrorMessage.INVALID_DETAILS);
        }

        if(signUpDetails.getAuthorId().isBlank() || signUpDetails.getPassword().isBlank()
        || signUpDetails.getFirstName().isBlank() || signUpDetails.getLastName().isBlank()){
            throw new BadRequestException(ErrorMessage.INVALID_DETAILS);
        }

    }

}
