package com.app.devblogservice.controlleradvice;

import com.app.devblogservice.exception.AuthorIdExistsException;
import com.app.devblogservice.model.BlogServiceErrorResponse;
import com.app.devblogservice.model.builder.BlogServiceErrorResponseBuilder;
import com.app.devblogservice.util.Constants;
import com.app.devblogservice.exception.DatabaseConnectivityException;
import com.app.devblogservice.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
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

    @ExceptionHandler(DatabaseConnectivityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BlogServiceErrorResponse> handleServeError(Exception ex){
        BlogServiceErrorResponse errorResponse = new BlogServiceErrorResponseBuilder()
                .status(Constants.FAILED)
                .message("Server Error")
                .errorCode(Constants.SERVER_ERROR)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorIdExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BlogServiceErrorResponse> serveInvalidAuthorIdException(Exception ex){
        BlogServiceErrorResponse errorResponse = new BlogServiceErrorResponseBuilder()
                .status(Constants.FAILED)
                .message("user id already exists")
                .errorCode(Constants.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
