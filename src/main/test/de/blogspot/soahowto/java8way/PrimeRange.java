package de.blogspot.soahowto.java8way;

import static de.blogspot.soahowto.java8way.Primes.isProbablePrime;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

import org.jooq.lambda.tuple.Tuple3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PrimeRange {

    private int certainty = 10;

    @Parameters
    public static Iterable<Tuple3<Long, Long, Long>> data()
    {
        return Arrays.asList(
                new Tuple3<>(0L, 1_000_000L, 78_498L),
                new Tuple3<>(1_000_001L, 2_000_000L, 70_435L),
                new Tuple3<>(2_000_001L, 3_000_000L, 67_883L),
                new Tuple3<>(100_000_001L, 101_000_000L, 54_208L)
                );
    }

    public PrimeRange(Tuple3<Long, Long, Long> input) {
        from = input.v1();
        to = input.v2();
        expected = input.v3();
    }

    private long from;

    private long to;

    private long expected;

    @Test
    public void countStrict() {
        countPrimes(Primes::isPrime);
    }

    @Test
    public void countProbable() {
        countPrimes(n -> isProbablePrime(n, certainty));
    }

    @Test
    public void countStrictParallel() {
        countPrimesParallel(Primes::isPrime);
    }

    @Test
    public void countProbableParallel() {
        countPrimesParallel(n -> isProbablePrime(n, certainty));
    }

    private void countPrimes(LongPredicate primeTest) {
        long count = LongStream.rangeClosed(from, to).filter(primeTest).count();

        assertThat(count).isEqualTo(expected);
    }

    private void countPrimesParallel(LongPredicate primeTest) {
        long count = LongStream.rangeClosed(from, to).parallel().filter(primeTest).count();

        assertThat(count).isEqualTo(expected);
    }

}
