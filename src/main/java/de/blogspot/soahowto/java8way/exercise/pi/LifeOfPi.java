package de.blogspot.soahowto.java8way.exercise.pi;

import org.jooq.lambda.Seq;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class LifeOfPi {
    private static final BigDecimal ONE = BigDecimal.ONE.setScale(25);

    private static final BigDecimal TWO = ONE.add(ONE);

    private static final BigDecimal FOUR = TWO.add(TWO);

    public Stream<BigDecimal> rootSequence() {
        //return Seq.iterate(TWO, n -> n.add(TWO)).zip(Seq.iterate(ONE, n -> n.negate()), (a,b) -> a.multiply(b));
        return Seq.iterate(2, n -> n + 2)
                .zip(Seq.iterate(1, n -> -n), (a,b) -> a*b)
                .map(BigDecimal::valueOf);
    }

    public Stream<BigDecimal> finalSequence() {
        return rootSequence();
    }

    public BigDecimal calculate() {
        return FOUR;
    }
}
