package de.blogspot.soahowto.java8way.excercise;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PalindromicPrimes {

    @Test
    public void base10() {
        Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime)
                .map(Object::toString)
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .limit(100)
                .forEach(System.out::println);
    }

    @Test
    public void base2() {
        Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime)
                .filter(i -> i.toString(2).equals(new StringBuilder(i.toString(2)).reverse().toString()))
                .limit(100)
                .forEach(i -> System.out.println(i + " (" + i.toString(2) + ")"));
    }

    @Test
    public void mutlipleBases() {
        List<Integer> digits = new ArrayList<>();
        digits.add(0);
        Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime)
                .filter(i -> i.toString().equals(new StringBuilder(i.toString()).reverse().toString()))
                .filter(i -> i.toString(2).equals(new StringBuilder(i.toString(2)).reverse().toString()))
                .limit(4)
                .forEach(i -> System.out.println(i + " (" + i.toString(2) + ")"));
    }

}
