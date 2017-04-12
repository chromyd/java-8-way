package de.blogspot.soahowto.java8way;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jooq.lambda.Unchecked;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class Cinema3D {
    private static final NumberFormat US_NUMBER_FORMAT = NumberFormat.getInstance(Locale.US);

    private static final Path PATH = Paths.get("movies.json");

    private Gson gson = new Gson();

    private List<Movie> movies = new ArrayList<>();

    @Before
    public void setup() throws IOException {
        for (String s : Files.readAllLines(PATH)) {
            movies.add(gson.fromJson(s, Movie.class));
        }
    }

    @Test
    public void countByYear() {
        Map<String, Long> totals = movies.stream().collect(groupingBy(Movie::getYear, counting()));
        System.out.println(totals);
    }

    @Test
    public void countByDecade() {
        movies.stream()
                .collect(Collectors.groupingBy(m -> m.getYear().substring(0, 3) + "0", Collectors.counting()))
                .forEach((key, value) -> System.out.printf("Decade %s-%.3s9: %2d\n", key, key, value));
    }

    @Test
    public void countActorAppearances() {
        //.peek(m -> System.out.println(m + " with " + m.getActors()))
        //.peek(System.out::println)
        System.out.println(movies.stream()
                .map(Movie::getActors)
                .flatMap(Pattern.compile(", *")::splitAsStream)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting())));
    }

    @Test
    public void findTopTenActors() {
        Map<String, Long> actorAppearances = movies.stream()
                .map(Movie::getActors)
                .flatMap(Pattern.compile(", *")::splitAsStream)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        actorAppearances.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void findTopDirectors() {
        findTop(10, Movie::getDirector);
    }

    @Test
    public void findTopWriters() {
        findTop(10, Movie::getWriter);
    }

    @Test
    public void findTopWritersClean() {
        findTop(10, m -> m.getWriter().replaceAll(" ?\\([^\\)]*\\)", ""));
    }

    @Test
    public void findTopCountries() {
        findTop(10, Movie::getCountry);
    }

    @Test
    public void findTopLanguages() {
        findTop(10, Movie::getLanguage);
    }

    @Test
    public void findTopGenres() {
        findTop(10, Movie::getGenre);
    }

    private void findTop(int n, Function<Movie, String> attributeSelector) {
        Map<String, Long> attributeCounts = movies.stream()
                //.peek(m -> System.out.println("B:" + m.getWriter()))
                .map(attributeSelector)
                //.peek(m -> System.out.println("A:" + m))
                .flatMap(s -> Pattern.compile(", *").splitAsStream(s).distinct())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        attributeCounts.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(n)
                .forEach(System.out::println);
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
        movies.stream()
                .filter(m -> m.getActors().contains("Tom Hanks"))
                .filter(m -> m.getActors().contains("Leonardo DiCaprio"))
                .forEach(m -> System.out.println(m + " with " + m.getActors()));
    }

    @Test
    public void averageVotesByDecade() {
        Map<String, Double> totals = movies.stream()
                .collect(
                        Collectors.groupingBy(
                                m -> m.getYear().substring(0, 3) + "0",
                                Collectors.averagingInt(unbox(
                                        Unchecked.function(
                                                (Movie m) -> US_NUMBER_FORMAT.parse(m.getImdbVotes()).intValue()
                                                )
                                        ))
                                )
                );
        totals.forEach((key, value) -> System.out.printf("Decade %s-%.3s9: %,7.0f\n", key, key, value));
    }

    /**
     * Adapts a {@code Function<T, Integer>} to a {@code ToIntFunction<T>}.
     * 
     * @param f
     *            A function to be adapted
     * @return adapted function
     */
    private <T> ToIntFunction<T> unbox(Function<T, Integer> f) {
        return f::apply;
    }

}
