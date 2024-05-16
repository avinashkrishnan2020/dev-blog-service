package com.app.devblogservice;

import com.app.devblogservice.exception.BadRequestException;
import com.app.devblogservice.model.LoginDetails;
import com.app.devblogservice.repository.AuthorRepository;
import com.app.devblogservice.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    LoginService loginService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenLoginDetailsNullThrowBadRequestException(){
        Assertions.assertThrows(BadRequestException.class, () -> {
            loginService.verifyLoginDetails(null);
        });
    }

    @Test
    public void whenPasswordBlankThrowBadRequestException(){
        Assertions.assertThrows(BadRequestException.class, () -> {
            loginService.verifyLoginDetails(new LoginDetails("chris123@yopmail.com", " "));
        });
    }

    @Test
    public void whenUsernameBlankThrowBadRequestException(){
        Assertions.assertThrows(BadRequestException.class, () -> {
            loginService.verifyLoginDetails(new LoginDetails(" ", "1234"));
        });
    }


}
