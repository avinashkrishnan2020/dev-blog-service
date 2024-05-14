package com.app.devblogservice.service;

import com.app.devblogservice.exception.DatabaseConnectivityException;
import com.app.devblogservice.model.Article;
import com.app.devblogservice.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewArticlesService {

    private final ArticleRepository articleRepository;

    public ViewArticlesService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() throws DatabaseConnectivityException {
        try {
            List<Article> articles = this.articleRepository.findAll();
            return articles;
        } catch(Exception ex){
            throw new DatabaseConnectivityException();
        }
    }

    public List<Article> getAllArticlesByAuthor(String author) throws DatabaseConnectivityException {
        try {
            List<Article> articles = this.articleRepository.findByAuthor(author);
            return articles;
        } catch(Exception ex){
            throw new DatabaseConnectivityException();
        }
    }
}
