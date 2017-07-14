package de.blogspot.soahowto.java8way;

import static de.blogspot.soahowto.java8way.Primes.isPrime;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class SomePrimes {

    @Test
    public void maxInt() {
        assertThat(isPrime(Integer.MAX_VALUE)).isTrue();
    }
}
