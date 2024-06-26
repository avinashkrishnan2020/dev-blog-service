package com.app.devblogservice.controlleradvice;

import com.app.devblogservice.exception.AuthorExistsException;
import com.app.devblogservice.exception.LoginFailedException;
import com.app.devblogservice.model.BlogServiceErrorResponse;
import com.app.devblogservice.model.builder.BlogServiceErrorResponseBuilder;
import com.app.devblogservice.util.Constants;
import com.app.devblogservice.exception.DatabaseConnectivityException;
import com.app.devblogservice.exception.InvalidRequestException;
import com.app.devblogservice.util.ErrorMessage;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class BlogServiceControllerAdvice {

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BlogServiceErrorResponse> serveInvalidRequestException(Exception ex){
        BlogServiceErrorResponse errorResponse = new BlogServiceErrorResponseBuilder()
                .status(Constants.FAILED)
                .message(ex.getMessage())
                .errorCode(Constants.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(TransientPropertyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BlogServiceErrorResponse> handleServeError(Exception ex){
        BlogServiceErrorResponse errorResponse = new BlogServiceErrorResponseBuilder()
                .status(Constants.FAILED)
                .message(ErrorMessage.INVALID_DETAILS)
                .errorCode(Constants.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BlogServiceErrorResponse> serveAuthorExistsException(Exception ex){
        BlogServiceErrorResponse errorResponse = new BlogServiceErrorResponseBuilder()
                .status(Constants.FAILED)
                .message(ErrorMessage.AUTHOR_EXISTS)
                .errorCode(Constants.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BlogServiceErrorResponse> serveLoginFailedException(Exception ex){
        BlogServiceErrorResponse errorResponse = new BlogServiceErrorResponseBuilder()
                .status(Constants.FAILED)
                .message(ex.getMessage())
                .errorCode(Constants.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
}
