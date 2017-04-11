package de.blogspot.soahowto.java8way;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Primes {

    private Primes() {
    }

    public static boolean isPrime(long n) {

        if (n <= 2)
            return n == 2;

        return n % 2 != 0 &&
                LongStream.iterate(3L, i -> i + 2)
                        .limit(Math.round(Math.sqrt(n) / 2) - 1)
                        //.peek(c -> System.out.printf("Peeking at %d with %d\n", c, n))
                        .noneMatch(c -> n % c == 0);
    }

    /**
     * Different implementation than {@link #isPrime(long)} because of
     * http://stackoverflow.com/questions/30825708/java-8-using-parallel-in-a-stream-causes-oom-error
     */
    public static boolean isPrimeFast(long n) {
        if (n <= 2)
            return n == 2;

        return n % 2 != 0 &&
                LongStream.range(1L, Math.round(Math.sqrt(n) / 2))
                        .parallel()
                        .map(c -> 2 * c + 1)
                        //.peek(c -> System.out.printf("Peeking at %d with %d\n", c, n))
                        .noneMatch(c -> n % c == 0);

    }

    public static boolean isAlmostCertainPrime(long n) {
        return BigInteger.valueOf(n).isProbablePrime(Integer.MAX_VALUE);
    }

    public static boolean isProbablePrime(long n, int certainty) {
        return BigInteger.valueOf(n).isProbablePrime(certainty);
    }
}
