package de.blogspot.soahowto.java8way;

import static de.blogspot.soahowto.java8way.Primes.isAlmostCertainPrime;
import static de.blogspot.soahowto.java8way.Primes.isPrime;
import static de.blogspot.soahowto.java8way.Primes.isProbablePrime;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FastPrimality {

    public static class LongRange implements Iterable<Long> {

        private long from;

        private long to;

        public LongRange(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("range <%,d - %,d)", from, to);
        }

        @Override
        public Iterator<Long> iterator() {
            return LongStream.range(from, to).iterator();
        }

    }

    @Parameters(name = "{index}: {0}")
    public static Iterable<Iterable<Long>> data()
    {
        return ranges(0L, 10_000_000L, 100);
        //return Arrays.asList(LongStream.range(0L, 500_000L)::iterator);
    }

    public static Iterable<Iterable<Long>> ranges(long from, long to, int rangeCount) {
        List<Iterable<Long>> ranges = new ArrayList<>(rangeCount);
        long pageSize = (to - from) / rangeCount;
        for (int i = 0; i < rangeCount; ++i) {
            ranges.add(new LongRange(i * pageSize, (i + 1) * pageSize));
        }
        return ranges;
    }

    /*
     * public FastPrimality(long input) { this.input = input; }
     */

    public FastPrimality(Iterable<Long> range) {
        this.range = range;
    }

    private Iterable<Long> range;

    @Test
    public void correct() {
        for (long input : range) {
            assertThat(!isProbablePrime(input, 6) || isPrime(input)).as("Checking %d", input).isTrue();
        }
    }

    @Test
    @Ignore
    public void correctTrust() {
        for (long input : range) {
            assertThat(!isAlmostCertainPrime(input)).as("Checking %d", input).isNotNull();
        }
    }

}
