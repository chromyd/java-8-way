package de.blogspot.soahowto.java8way.exercise.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;

import static de.blogspot.soahowto.java8way.exercise.pi.LifeOfPi.THREE;

public class LifeOfPiAnalyzer {
    private static final String PI = "14159265358979323846264338327950288419716939937510";

    private LifeOfPi lifeOfPi = new LifeOfPi();

    public static void main(String[] argv) {
        new LifeOfPiAnalyzer().analyze();
    }

    private void analyze() {
        for (long iterations = 1000; true; iterations *= 2) {
            Instant start = Instant.now();
            String pi = lifeOfPi.finalSequence().limit(iterations).reduce(THREE, BigDecimal::add).setScale(25, RoundingMode.HALF_UP).toString().substring(2);
            int accuracy = 0;
            while (pi.startsWith(PI.substring(0, ++accuracy))) {
            }
            --accuracy;
            System.out.printf("Iterations: %,11d Accuracy: %2d (in %s)\n", iterations, accuracy, Duration.between(start, Instant.now()).toString().substring(2).toLowerCase());
            if (accuracy >= 25) {
                break;
            }
        }
    }
}
