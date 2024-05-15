package com.app.devblogservice.service;

import com.app.devblogservice.exception.InvalidRequestException;
import com.app.devblogservice.model.Article;
import com.app.devblogservice.repository.ArticleRepository;
import com.app.devblogservice.util.ErrorMessage;
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

    public Article publishNewArticle(Article newArticle) throws InvalidRequestException {
        sanitizeRequest(newArticle);
        newArticle.setPublishAt(LocalDateTime.now());
        return this.articleRepository.save(newArticle);
    }

    public void sanitizeRequest(Article newArticle) throws InvalidRequestException {
        if(null == newArticle){
            throw new InvalidRequestException(ErrorMessage.INVALID_REQUEST);
        }
    }
}
