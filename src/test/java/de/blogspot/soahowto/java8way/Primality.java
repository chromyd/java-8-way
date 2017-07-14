package de.blogspot.soahowto.java8way;

import static de.blogspot.soahowto.java8way.Primes.isAlmostCertainPrime;
import static de.blogspot.soahowto.java8way.Primes.isPrime;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Primality {

    @Parameters
    public static List<Long> data()
    {
        return LongStream.range(0, 50_000).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public Primality(long input) {
        this.input = input;
    }

    private long input;

    @Test
    public void correct() {
        assertThat(isPrime(input)).isEqualTo(isAlmostCertainPrime(input));
    }
}
