package de.blogspot.soahowto.java8way.exercise.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;

public class LambdaComparatorTest {

    List<Integer> naturals = Arrays.asList(5, 10, 16, 3, 9, 12, 8, 19, 14, 15, 11, 6, 1, 2, 18, 13, 4, 7, 17, 20);

    List<Integer> integers = Arrays.asList(9, -9, 0, 5, -5, 4, 7, 1, -3, -8, -7, -2, -6, -4, -10, 8, 2, -1, 3, 6, 10);

    @Test
    public void descending()
    {
        integers.sort(null);
        System.out.println(integers);
        assertThat(integers).isEqualTo(
                Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10));
    }

    @Test
    public void lexicographical()
    {
        naturals.sort(null);
        System.out.println(naturals);
        assertThat(naturals).isEqualTo(
                Arrays.asList(1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2, 20, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void absoluteValue()
    {
        integers.sort(null);
        System.out.println(integers);
        assertThat(integers).isEqualTo(
                Arrays.asList(0, 1, -1, -2, 2, -3, 3, 4, -4, 5, -5, -6, 6, 7, -7, -8, 8, 9, -9, -10, 10));
    }

    @Test
    public void evenBeforeOdd() {
        naturals.sort(null);
        System.out.println(naturals);
        assertThat(naturals).isEqualTo(
                Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19));
    }

    @Test
    public void absoluteValueWithPositiveBias()
    {
        integers.sort(null);
        System.out.println(integers);
        assertThat(integers).isEqualTo(
                Arrays.asList(0, 1, -1, 2, -2, 3, -3, 4, -4, 5, -5, 6, -6, 7, -7, 8, -8, 9, -9, 10, -10));
    }

    @Test
    @Ignore
    public void generateRandomSequences()
    {
        shuffleAndPrint(IntStream.rangeClosed(1, 20));
        shuffleAndPrint(IntStream.rangeClosed(-10, 10));
    }

    private void shuffleAndPrint(IntStream stream) {
        List<Integer> numbers = stream.boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        System.out.println(numbers);
    }

}
