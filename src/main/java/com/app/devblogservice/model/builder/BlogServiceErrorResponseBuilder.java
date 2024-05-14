package com.app.devblogservice.model.builder;

import com.app.devblogservice.model.BlogServiceErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BlogServiceErrorResponseBuilder {
    private String status;
    private String message;
    private String errorCode;

    public BlogServiceErrorResponseBuilder status(String status){
        this.status = status;
        return this;
    }

    public BlogServiceErrorResponseBuilder message(String message){
        this.message = message;
        return this;
    }

    public BlogServiceErrorResponseBuilder errorCode(String errorCode){
        this.errorCode = errorCode;
        return this;
    }

    public BlogServiceErrorResponse build(){
        return new BlogServiceErrorResponse(this.status, this.message, this.errorCode);
    }
}
