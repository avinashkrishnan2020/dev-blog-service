package com.app.devblogservice.publish.service;

import com.app.devblogservice.publish.model.Article;
import com.app.devblogservice.publish.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticlePublishService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticlePublishService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public Article publishNewArticle(Article newArticle){
        newArticle.setPublishAt(LocalDateTime.now());
        return this.articleRepository.save(newArticle);
    }
}
