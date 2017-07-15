package de.blogspot.soahowto.java8way;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FlattenTest {
    private Stream<List> listStream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5), Arrays.asList(6));

    @Test
    public void flatNumbersSimple() {
        Stream<Integer> flatStream = listStream.flatMap(Collection::stream);
        assertThat(flatStream).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void flatNumbersElegant() {
        Stream<Integer> flatStream = listStream.map(Collection::stream).flatMap(n -> n);
        assertThat(flatStream).containsExactly(1, 2, 3, 4, 5, 6);
    }

}
