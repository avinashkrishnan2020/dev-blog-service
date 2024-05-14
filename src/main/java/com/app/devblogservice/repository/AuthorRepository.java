package com.app.devblogservice.repository;

import com.app.devblogservice.model.Author;
import com.app.devblogservice.model.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query("select a from Author a where a.authorId= :username AND a.password= :password")
    public Author findByAuthorIdAndPassword(String username, String password);
}
