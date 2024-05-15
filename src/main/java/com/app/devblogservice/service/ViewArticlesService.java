package com.app.devblogservice.service;

import com.app.devblogservice.exception.DatabaseConnectivityException;
import com.app.devblogservice.model.Article;
import com.app.devblogservice.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViewArticlesService {

    private final ArticleRepository articleRepository;

    public ViewArticlesService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return Optional.ofNullable(this.articleRepository.findAll())
                .orElse(new ArrayList<>());

    }

    public List<Article> getAllArticlesByAuthor(String authorId) {

        return Optional.ofNullable(this.articleRepository.findByAuthor(authorId))
                .orElse(new ArrayList<>());
    }
}
