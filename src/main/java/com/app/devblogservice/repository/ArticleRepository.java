package com.app.devblogservice.repository;

import com.app.devblogservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("Select a from Article a where a.author.authorId = :authorId")
    public List<Article> findByAuthor(@Param("authorId") String authorId);

}
