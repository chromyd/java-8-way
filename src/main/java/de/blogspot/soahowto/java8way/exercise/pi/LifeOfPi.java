package de.blogspot.soahowto.java8way.exercise.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class LifeOfPi {
    private static final String PI = "14159265358979323846264338327950288419716939937510";

    private static final BigDecimal ONE = BigDecimal.ONE.setScale(27);

    private static final BigDecimal TWO = ONE.add(ONE);
    private static final BigDecimal THREE = TWO.add(ONE);

    private static final BigDecimal FOUR = TWO.add(TWO);

    public Stream<BigDecimal> rootSequence() {
        return Stream.iterate(2, n -> n + 4)
                .flatMap(n -> Stream.of(n, -(n + 2)))
                .map(BigDecimal::valueOf);
    }

    public Stream<BigDecimal> finalSequence() {
        return rootSequence()
                .map(n -> FOUR.divide(n.multiply(n.abs().add(ONE).multiply(n.abs().add(TWO))), RoundingMode.HALF_UP));
    }

    public BigDecimal calculate() {
        return finalSequence()
                .limit(6_587_000)
                .reduce(THREE, BigDecimal::add)
                .setScale(20, RoundingMode.HALF_UP);
    }

    public static void main(String[] argv) {
        new LifeOfPi().analyze();
    }

    private void analyze() {
        Instant start = Instant.now();
        int accuracy = 0;
        for (long iterations = 1000; true; iterations *= 2) {
            String pi = finalSequence().limit(iterations).reduce(THREE, BigDecimal::add).setScale(25, RoundingMode.HALF_UP).toString().substring(2);
            int newAccuracy = 0;
            while (pi.startsWith(PI.substring(0, newAccuracy))) {
                ++newAccuracy;
            }
            --newAccuracy;
            if (newAccuracy == accuracy) {
                continue;
            }
            if (newAccuracy < accuracy) {
                System.out.println("WHOA!! IT DID HAPPEN!!!!!!!!!!!!!! AT ITERATION COUNT: " + iterations);
            }
            if (newAccuracy > accuracy) {
                accuracy = newAccuracy;
                System.out.printf("Iterations: %,9d Accuracy: %2d\n", iterations, accuracy);
            }
            if (accuracy >= 25) {
                break;
            }
        }
        System.out.println("All done in: " + (Duration.between(start, Instant.now())));
    }

}
