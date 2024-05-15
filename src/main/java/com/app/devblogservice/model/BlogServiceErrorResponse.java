package com.app.devblogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BlogServiceErrorResponse {

    private String status;
    private String message;
    private String errorCode;

}
