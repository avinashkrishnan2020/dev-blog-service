package com.app.devblogservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BlogServiceErrorResponse {

    private String status;
    private String message;
    private String errorCode;

}
