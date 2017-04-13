package de.blogspot.soahowto.java8way;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.LongStream;

import org.junit.Test;

public class Streams {

    @Test
    public void factorials() {
        assertThat(fact(0)).isEqualTo(factorial(0));
        assertThat(fact(15)).isEqualTo(factorial(15));
    }

    private long fact(long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

    private long factorial(long n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
}
