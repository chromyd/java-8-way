package de.blogspot.soahowto.java8way.exercise.pi;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class LifeOfPi {
    private static final BigDecimal ONE = BigDecimal.ONE.setScale(25);

    private static final BigDecimal TWO = ONE.add(ONE);

    private static final BigDecimal FOUR = TWO.add(TWO);

    public Stream<BigDecimal> rootSequence() {
        return Stream.iterate(2, n -> n + 4)
                .flatMap(n -> Stream.of(n, -(n + 2)))
                .map(BigDecimal::valueOf);
    }

    public Stream<BigDecimal> finalSequence() {
        return rootSequence();
    }

    public BigDecimal calculate() {
        return FOUR;
    }
}
