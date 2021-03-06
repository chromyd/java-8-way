package de.blogspot.soahowto.java8way;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Cinematic {

    private static final Path PATH = Paths.get("movies.json");

    private Gson gson = new Gson();

    private List<Movie> movies;

    @Before
    public void setup() throws IOException {
        movies = Files.lines(PATH).map(s -> gson.fromJson(s, Movie.class)).collect(Collectors.toList());
    }

    @Test
    public void metaChart() throws JsonSyntaxException, IOException {
        Files.lines(PATH).map(s -> gson.fromJson(s, Movie.class))
                .forEach(m -> System.out.println(m + ", " + m.allRatings()));
    }

    @Test
    public void chartByMetacritic() {
        movies.sort(comparing(m -> m.getMetacriticScore().orElse(null), nullsLast(reverseOrder())));
        movies.stream().forEach(show_ranked);
    }

    @Test
    public void chartByRottenTomatoes() {
        movies.sort(comparing(m -> m.getRottenScore().orElse(null), nullsLast(reverseOrder())));
        movies.stream().forEach(show_ranked);
    }

    @Test
    public void chartByRottenThenImdb() {
        movies.stream()
                .sorted(comparing((Movie m) -> m.getRottenScore().orElse(null), nullsLast(reverseOrder()))
                        .thenComparing(Movie::getImdbRating, reverseOrder()))
                .forEach(show_ranked);

    }

    private final Consumer<Movie> show_ranked = new Consumer<Movie>() {

        private int rank = 0;

        @Override
        public void accept(Movie t) {
            System.out.printf("%3d. %s\n", ++rank, t);
        }
    };

}
