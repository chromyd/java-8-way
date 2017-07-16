package de.blogspot.soahowto.java8way.exercise.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static java.math.BigDecimal.ZERO;

public class LifeOfPi {
    protected static final BigDecimal ONE = BigDecimal.ONE.setScale(25);

    protected static final BigDecimal TWO = ONE.add(ONE);
    protected static final BigDecimal THREE = TWO.add(ONE);

    protected static final BigDecimal FOUR = TWO.add(TWO);

    public Stream<BigDecimal> rootSequence() {
        return Stream.iterate(2, n -> n + 4)
                .flatMap(n -> Stream.of(n, -(n + 2)))
                .map(BigDecimal::valueOf);
    }

    public Stream<BigDecimal> finalSequence() {
        return rootSequence()
                .unordered()
                .parallel()
                .map(n -> FOUR.divide(
                        n.multiply(n.abs().add(ONE)).multiply(n.abs().add(TWO)),
                        RoundingMode.HALF_UP));
    }

    public BigDecimal calculate() {
        return finalSequence()
                .limit(6_587_000)
                .reduce(ZERO, BigDecimal::add)
                .add(THREE)
                .setScale(20, RoundingMode.HALF_UP);
    }
}
