package de.blogspot.soahowto.java8way.exercise.prime;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.stream.Collectors;

import org.junit.Test;

public class TheSolitudeOfPrimeNumbersTest {

    private TheSolitudeOfPrimeNumbers primes = new TheSolitudeOfPrimeNumbers();

    @Test
    public void primes() {
        assertThat(primes.primes().map(BigInteger::intValue).limit(1000).collect(Collectors.toList()))
                .startsWith(2, 3, 5, 7, 11)
                .endsWith(7901, 7907, 7919);
    }

    @Test
    public void palindromicPrimes() {
        assertThat(primes.palindromicPrimes().map(BigInteger::intValue).limit(100).collect(Collectors.toList()))
                .startsWith(2, 3, 5, 7, 11, 101, 131, 151, 181, 191, 313, 353, 373, 383, 727, 757, 787, 797, 919, 929);
    }

    @Test
    public void mersennePrimes() {
        assertThat(primes.mersennePrimes().map(BigInteger::toString).limit(10).collect(Collectors.toList()))
                .containsExactly("3", "7", "31", "127", "8191", "131071", "524287", "2147483647",
                        "2305843009213693951", "618970019642690137449562111");
    }

    @Test
    public void twinPrimes() {
        assertThat(primes.twinPrimes().map(BigInteger::intValue).limit(100).collect(Collectors.toList()))
                .startsWith(3, 5, 7, 11, 13, 17, 19, 29, 31, 41, 43, 59, 61, 71, 73, 101, 103, 107, 109, 137, 139);
    }

    @Test
    public void twinPrimePairs() {
        assertThat(primes.twinPrimePairs().map(BigInteger::intValue).limit(100).collect(Collectors.toList()))
                .startsWith(3, 5, 5, 7, 11, 13, 17, 19, 29, 31, 41, 43, 59, 61, 71, 73, 101, 103, 107, 109, 137, 139);
    }

}
