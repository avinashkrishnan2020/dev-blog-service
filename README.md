# Tech Blog API

A simple project using Java and Spring Boot to create author profiles, and to publish and read tech articles.


# Requirements
- Java 21
- Maven
- Postman
- IDE (IntelliJ preferred)
- RDBMS of your choice (Postgres used here)

# Running the application
- Clone the project and open it in an IDE of your choice (IntelliJ preferred)
- Open application.properties
  - Provide datasource url
  - Provide username and password for your data source
  - Provide hibernate dialect as per the RDBMS used in your system
- Run the following script to create tables in your DB
  - ```sql
    
    CREATE TABLE author (
        author_id VARCHAR(100) PRIMARY KEY,
        first_name VARCHAR(100) NOT NULL,
        last_name VARCHAR(100) NOT NULL,
        password VARCHAR(255) NOT NULL
    );
    
    CREATE TABLE article (
        article_id SERIAL PRIMARY KEY,
        title VARCHAR(50) NOT NULL,
        body TEXT NOT NULL,
        publish_at TIMESTAMP NOT NULL,
        author_id VARCHAR(100) NOT NULL,
        FOREIGN KEY(author_id) REFERENCES author.author_id CASCADE ON DELETE
    );
    
    # Sequence for automatically incrementing primary key in article table
    create sequence article_seq START WITH 1 INCREMENT BY 1;
    
    # Add the sequence to article table
    ALTER TABLE article ALTER COLUMN article_id SET DEFAULT nextval('article_seq');
    
    ```
- Run the application


## API Documentation
- Documentation for the API can be found here: http://localhost:8081/swagger-ui/index.html
  - This link provides the following details
    - API contract
    - Error Responses
  - The link can also be used to try out sample requests.
- JSON documentation can be viewed here: http://localhost:8081/api-docs