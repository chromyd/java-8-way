package de.blogspot.soahowto.java8way.exercise.movie;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.junit.Test;

import de.blogspot.soahowto.java8way.Movie;

public class StreamingMoviesTest {

    private StreamingMovies streamingMovies = new StreamingMovies();

    @Test
    public void countMovies() {
        assertThat(streamingMovies.countMovies()).isEqualTo(250);
    }

    @Test
    public void findMoviesWithActor() {
        assertThat(streamingMovies.findMoviesWithActor("Brad Pitt").stream().map(Movie::getTitle).collect(toList()))
                .contains("Fight Club", "Snatch");
    }

    @Test
    public void findBestMovieByCountry() {
        assertThat(streamingMovies.findBestMovieByCountry("Japan").map(Movie::getTitle))
                .hasValue("Seven Samurai");
        assertThat(streamingMovies.findBestMovieByCountry("Brazil").map(Movie::getTitle))
                .hasValue("City of God");
        assertThat(streamingMovies.findBestMovieByCountry("Czechia").map(Movie::getTitle))
                .isEmpty();
    }

    @Test
    public void countByYear() {
        assertThat(streamingMovies.countByYear())
                .contains(entry("1963", 2L))
                .contains(entry("1972", 1L))
                .contains(entry("1973", 1L))
                .contains(entry("1995", 9L))
                .contains(entry("2001", 6L));
    }

    @Test
    public void countByDecade() {
        assertThat(streamingMovies.countByDecade())
                .contains(entry("1920", 6L))
                .contains(entry("1950", 26L))
                .contains(entry("2000", 52L));
    }

    @Test
    public void bestByDecade() {
        assertThat(streamingMovies.bestByDecade())
                .hasEntrySatisfying("1920", m -> m.get().getTitle().equals("The Kid"))
                .hasEntrySatisfying("1930", m -> m.get().getTitle().equals("City Lights"))
                .hasEntrySatisfying("1990", m -> m.get().getTitle().equals("The Shawshank Redemption"))
                .hasEntrySatisfying("2000", m -> m.get().getTitle().equals("The Dark Knight"));
    }
}
