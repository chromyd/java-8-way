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
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Cinematic {

    private static final Path PATH = Paths.get("movies.json");

    private Gson gson = new Gson();

    private List<Movie> movies;

    private final Consumer<Movie> show_ranked = new Consumer<Movie>() {

        private int rank = 0;

        @Override
        public void accept(Movie t) {
            System.out.printf("%3d. %s\n", ++rank, t);
        }
    };

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
        movies.stream().forEach(System.out::println);
    }

    @Test
    public void chartByRotten() {
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

    @Test
    @Ignore
    public void fiddlingWithGson() {
        Gson g = new Gson();

        Person person = g.fromJson("{\"name\": \"John\"}", Person.class);
        System.out.println(person.getName()); //John

        System.out.println(g.toJson(person)); // {"name":"John"}
    }

    @Test
    @Ignore
    public void fiddlingWithGsonMovie() throws IOException {
        Gson g = new Gson();

        Movie m = g.fromJson("{\"Title\": \"Fun with me\", \"year\": \"2017\"}".toLowerCase(), Movie.class);
        System.out.println(m);

        System.out.println(g.toJson(m));
    }

    @Test
    @Ignore
    public void fiddlingWithGsonTrueMovie() throws IOException {
        String movieString = Files.lines(PATH).limit(1).findAny().get();
        System.out.println(movieString);

        Gson g = new Gson();

        Movie m = g.fromJson(movieString.toLowerCase(), Movie.class);
        System.out.println(m);

        System.out.println(g.toJson(m));
    }

}
