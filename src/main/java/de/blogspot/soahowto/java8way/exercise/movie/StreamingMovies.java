package de.blogspot.soahowto.java8way.exercise.movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import de.blogspot.soahowto.java8way.Movie;

public class StreamingMovies {

    private List<Movie> movies;

    public StreamingMovies() {
        Gson gson = new Gson();
        Path path = Paths.get("movies.json");
        try {
            movies = Files.lines(path).map(s -> gson.fromJson(s, Movie.class)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int countMovies() {
        return movies.size();
    }

    public List<Movie> findMoviesWithActor(String actorName) {
        return Collections.emptyList();
    }

    public Optional<Movie> findBestMovieByCountry(String country) {
        return Optional.empty();
    }

    public Map<String, Long> countByYear() {
        return Collections.emptyMap();
    }

    public Map<String, Long> countByDecade() {
        return Collections.emptyMap();
    }

    public Map<String, Optional<Movie>> bestByDecade() {
        return Collections.emptyMap();
    }

}
