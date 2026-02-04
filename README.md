# practice-movie-description-noah-b
This project ties together our learnings on Spring Boot, MySQL Workbench, Postman, and API Integration.  

Description of App  

A simple web app where users can:  
● Add movies (title + rating)  
● AI automatically creates descriptions  
● View all movies with AI-generated descriptions  

AI Integration  

● Use Google Gemini API to generate movie descriptions  
● When user adds a movie title, AI creates the description automatically  

Local Setup  

* Database: create a schema called "movies"  
* Environment variables: define DB_USERNAME, DB_PASSWORD, and GOOGLE_API_KEY (separate with semicolons)   
* IDE: run MovieDescriptionApplication.java in src/main/java/com.example.Movie.Description/  
* Browser: visit this URL: http://localhost:8080/movies/add-movie 