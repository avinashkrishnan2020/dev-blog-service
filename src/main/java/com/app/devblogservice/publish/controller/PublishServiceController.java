package com.app.devblogservice.publish.controller;


import com.app.devblogservice.publish.model.Article;
import com.app.devblogservice.publish.service.ArticlePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev-blog-service")
public class PublishServiceController {

    private final ArticlePublishService articlePublishService;

    @Autowired
    public PublishServiceController(ArticlePublishService articlePublishService){
        this.articlePublishService = articlePublishService;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishArticle(@RequestBody Article newArticle){
        Article article = this.articlePublishService.publishNewArticle(newArticle);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
}
