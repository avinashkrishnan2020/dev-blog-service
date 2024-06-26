package com.app.devblogservice.controller;

import com.app.devblogservice.exception.DatabaseConnectivityException;
import com.app.devblogservice.exception.InvalidRequestException;
import com.app.devblogservice.model.Article;
import com.app.devblogservice.service.ArticlePublishService;
import com.app.devblogservice.service.ViewArticlesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class DevBlogController {

    private final ArticlePublishService articlePublishService;
    private final ViewArticlesService viewArticlesService;


    @Autowired
    public DevBlogController(ArticlePublishService articlePublishService,
                             ViewArticlesService viewArticlesService){
        this.articlePublishService = articlePublishService;
        this.viewArticlesService = viewArticlesService;
    }

    @PostMapping
    @Operation(summary = "API for posting/publishing an article")
    public ResponseEntity<Article> publishArticle(@RequestBody Article newArticle) throws InvalidRequestException {
        Article article = this.articlePublishService.publishNewArticle(newArticle);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }


    @GetMapping
    @Operation(summary = "API for retrieving all articles")
    public ResponseEntity<List<Article>> getAllArticles() throws DatabaseConnectivityException {
        return new ResponseEntity<>(this.viewArticlesService.getAllArticles(), HttpStatus.OK);
    }

    @GetMapping(params={"authorId"})
    @Operation(summary = "API for retrieving articles written by an author (using author id)")
    public ResponseEntity<List<Article>> getArticlesByAuthor(@RequestParam(name="authorId", required=true)String authorId) throws DatabaseConnectivityException {
        return new ResponseEntity<>(this.viewArticlesService.getAllArticlesByAuthor(authorId), HttpStatus.OK);
    }
}
