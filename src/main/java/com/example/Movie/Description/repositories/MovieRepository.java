package com.example.Movie.Description.repositories;

import com.example.Movie.Description.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
