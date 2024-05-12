package com.app.devblogservice.publish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

}
