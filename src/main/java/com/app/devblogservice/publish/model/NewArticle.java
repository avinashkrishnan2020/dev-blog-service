package com.app.devblogservice.publish.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NewArticle {
    private String title;
    private Author author;
    private Date publishDate;
}
