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
                        .peek(c -> System.out.printf("Peeking at %d with %d\n", c, n))
                        .noneMatch(c -> n % c == 0);
    }

    public static boolean isAlmostCertainPrime(long n) {
        return BigInteger.valueOf(n).isProbablePrime(Integer.MAX_VALUE);
    }

    public static boolean isProbablePrime(long n, int certainty) {
        return BigInteger.valueOf(n).isProbablePrime(certainty);
    }
}
