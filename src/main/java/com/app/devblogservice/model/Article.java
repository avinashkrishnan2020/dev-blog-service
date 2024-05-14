package com.app.devblogservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "article")
public class Article {
    @Id
    @Column(name="article_id")
    private String articleId;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Column(name="publish_at")
    private LocalDateTime publishAt;
    @Column(name="update_at")
    private LocalDateTime updateAt;

}
