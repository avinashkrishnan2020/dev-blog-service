package com.app.devblogservice;


import com.app.devblogservice.model.Article;
import com.app.devblogservice.model.Author;
import com.app.devblogservice.repository.ArticleRepository;
import com.app.devblogservice.service.ViewArticlesService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ViewArticlesServiceTest {

    @Mock
    public ArticleRepository articleRepository;

    @InjectMocks
    public ViewArticlesService viewArticleService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindAllCalledReturnAllArticles(){
        List<Article> mockList = getArticles();
        Mockito.when(articleRepository.findAll()).thenReturn(mockList);
        List<Article> actualList = viewArticleService.getAllArticles();
        Assertions.assertEquals(actualList.size(), mockList.size());
    }

    @Test
    public void whenFindByAuthorCalledReturnArticlesOfThatAuthorOnly(){
        List<Article> mockList = getArticlesByAuthor("chris.richardson@yopmail.com");
        Mockito.when(articleRepository.findByAuthor("chris.richardson@yopmail.com")).thenReturn(mockList);
        List<Article> actualList = viewArticleService.getAllArticlesByAuthor("chris.richardson@yopmail.com");
        Assertions.assertEquals(actualList.getFirst().getAuthor().getAuthorId(), mockList.getFirst().getAuthor().getAuthorId());
    }


    private List<Article> getArticles(){
        return Arrays.asList(
                new Article(1l, "article_1", new Author("avinash.krishann@yopmail.com", "Avinash", "Krishnan", "123")
                ,"text_1", LocalDateTime.now()),
                new Article(1l, "article_1", new Author("chris.richardson@yopmail.com", "Chris", "Richardson", "145623")
                        ,"text_1", LocalDateTime.now())
        );
    }

    private List<Article> getArticlesByAuthor(String id){
        return getArticles().stream()
                .filter(article -> article.getAuthor().getAuthorId().equals(id))
                .collect(Collectors.toList());
    }
}
