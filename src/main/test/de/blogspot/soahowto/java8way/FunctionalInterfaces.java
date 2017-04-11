package de.blogspot.soahowto.java8way;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class FunctionalInterfaces {
    @Test
    public void withRunnable() {
        new Thread(() -> System.out.println("Running!")).start();
    }

    @Test
    public void withCallable() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> answer = es.submit(() -> 42);
        System.out.println("The ultimate answer is " + answer.get());
    }

    @Test
    public void withCustom() {
        wrapAndPrint(s -> s + ", world");
    }

    @Test
    public void anotherCustom() {
        Timer.runTimed(() -> System.out.println(Primes.isPrime(Integer.MAX_VALUE - 6)));
    }

    @FunctionalInterface
    public static interface Wrapper {
        String wrap(String text);
    }

    private void wrapAndPrint(Wrapper w) {
        System.out.println(w.wrap("hello"));
    }

    public static interface Timer {
        void run();

        static void runTimed(Timer t) {
            long startTime = System.nanoTime();
            t.run();
            long finishTime = System.nanoTime();
            System.out.printf("Execution took %.3f ms\n", (finishTime - startTime) / 1_000_000.0);
        }
    }
}
