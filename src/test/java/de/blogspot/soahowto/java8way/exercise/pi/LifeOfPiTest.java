package de.blogspot.soahowto.java8way.exercise.pi;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.junit.Test;

public class LifeOfPiTest {

    private static final String PI = "3.1415926535897932384";

    private LifeOfPi lifeOfPi = new LifeOfPi();

    @Test
    public void rootSequence() {
        assertThat(lifeOfPi.rootSequence().map(BigDecimal::intValueExact).limit(10).collect(Collectors.toList()))
                .startsWith(2, -4, 6, -8, 10, -12);
    }

    @Test
    public void finalSequence() {
        assertThat(lifeOfPi.finalSequence().map(BigDecimal::doubleValue).limit(10).collect(Collectors.toList()))
                .startsWith(4.0 / (2 * 3 * 4), -4.0 / (4 * 5 * 6), 4.0 / (6 * 7 * 8), -4.0 / (8 * 9 * 10));
    }

    @Test
    public void calculate() {
        assertThat(lifeOfPi.calculate()).isEqualTo(PI);
    }
}
