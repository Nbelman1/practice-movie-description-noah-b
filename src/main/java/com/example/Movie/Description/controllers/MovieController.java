package com.example.Movie.Description.controllers;

import com.example.Movie.Description.models.Movie;
import com.example.Movie.Description.repositories.MovieRepository;
import com.example.Movie.Description.services.GeminiService;
import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    // dependency injection
    private final MovieRepository movieRepository;
    private final GeminiService geminiService;

    public MovieController(MovieRepository movieRepository, GeminiService geminiService) {
        this.movieRepository = movieRepository;
        this.geminiService = geminiService;
    }

    // renders html
    @GetMapping("/add-movie")
    @ResponseBody
    public String showTitleForm() {
        return """
                <html>
                    <body>
                        <h2>Welcome to Movie Scriptor!</h2>
                        
                        <form action="/movies/generate" method="post">
                            <input type="text" name="movieTitle" placeholder="Enter movie title">
                            <button type="submit">Generate description</button>
                        </form>
                    </body>
                </html>
                """;
    }

    @PostMapping("/generate")
    public String handleForm(@RequestParam("movieTitle") String title) throws HttpException, IOException {
        String description = geminiService.generateDescription(title);
        movieRepository.save(new Movie(title, description));
        return "Success!";
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            return movieRepository.save(movie);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieRepository.deleteById(id);
    }

}
