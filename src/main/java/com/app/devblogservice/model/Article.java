package com.app.devblogservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "article")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    @Id
    @Column(name="article_id")
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "article_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long articleId;
    @Column(name="title", nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;
    @Column(name="publish_at", nullable = false)
    private LocalDateTime publishAt;


}
