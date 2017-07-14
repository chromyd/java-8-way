package de.blogspot.soahowto.java8way.exercise.prime;

import static java.math.BigInteger.ONE;

import java.math.BigInteger;
import java.util.stream.Stream;

public class TheSolitudeOfPrimeNumbers {

    public static final BigInteger TWO = ONE.shiftLeft(1);

    public Stream<BigInteger> primes() {
        return Stream.of(TWO);
    }

    public Stream<BigInteger> palindromicPrimes() {
        return Stream.of(TWO);
    }

    public Stream<BigInteger> mersennePrimes() {
        return Stream.of(TWO);
    }

    public Stream<BigInteger> twinPrimes() {
        return Stream.of(TWO);
    }

    public Stream<BigInteger> twinPrimePairs() {
        return Stream.of(TWO);
    }
}
