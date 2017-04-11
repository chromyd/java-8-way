package de.blogspot.soahowto.java8way;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class Cinema3D {
    private static final Path PATH = Paths.get("movies.json");

    private Gson gson = new Gson();

    private List<Movie> movies;

    @Before
    public void setup() throws IOException {
        movies = Files.lines(PATH).map(s -> gson.fromJson(s, Movie.class)).collect(toList());
    }

    @Test
    public void countByYear() {
        Map<String, Long> totals = movies.stream().collect(
                groupingBy(Movie::getYear, counting()));
        System.out.println(totals);
    }

    @Test
    public void countByDecade() {
        Map<String, Long> totals = movies.stream().collect(
                groupingBy(m -> m.getYear().substring(0, 3) + "0", counting()));
        System.out.println(totals);
    }

    // movies with Kate Winslet
    @Test
    public void findByActor() {
        movies.stream()
                .filter(m -> m.getActors().contains("Kate Winslet"))
                .forEach(m -> System.out.println(m + " with " + m.getActors()));
    }

    // movies with Tom Hanks and Leonardo DiCaprio
    @Test
    public void findByActors() {
        System.out.println(movies.stream()
                .map(Movie::getActors)
                .flatMap(Pattern.compile(", *")::splitAsStream)
                .collect(groupingBy(Function.identity(), counting())));
    }

    @Test
    public void findTopTenActors() {
        Map<String, Long> actorTotals = movies.stream()
                .map(Movie::getActors)
                .flatMap(Pattern.compile(", *")::splitAsStream)
                .collect(groupingBy(a -> a, counting()));

        actorTotals.entrySet().stream()
                .sorted(comparing(Map.Entry::getValue, reverseOrder()))
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void findTopTenDirectors() {

    }

}
