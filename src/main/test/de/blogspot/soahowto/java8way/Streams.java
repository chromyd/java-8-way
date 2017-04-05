package de.blogspot.soahowto.java8way;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jooq.lambda.Seq.seq;

import java.util.stream.LongStream;

import org.jooq.lambda.Seq;
import org.junit.Test;

public class Streams {

    private static final long LIMIT = 10_000_000;

    @Test
    public void generate() {
        System.out.printf("Sum: %8.6e\n",
                new Long(LongStream.iterate(3L, n -> n + 2)
                        .limit(LIMIT / 2)
                        .reduce(Long::sum)
                        .getAsLong()
                ).doubleValue());
    }

    @Test
    public void generateSeq() {
        System.out.printf("Sum: %8.6e\n",
                Seq.iterate(3L, n -> n + 2)
                        .limitWhile(n -> n < LIMIT)
                        .reduce(Long::sum)
                        .map(Double::new)
                        .get()
                );
    }

    @Test
    public void generateSeqFast() {
        System.out.printf("Sum: %8.6e\n",
                seq(LongStream.iterate(3L, n -> n + 2))

                        .limitWhile(n -> n < LIMIT)
                        .reduce(Long::sum)
                        .map(Double::new)
                        .get()
                );
    }

    @Test
    public void count() {
        assertThat(Math.round(Math.sqrt(36) / 2) - 1).isEqualTo(2);
        assertThat(Math.round(Math.sqrt(40) / 2) - 1).isEqualTo(2);
        assertThat(Math.round(Math.sqrt(49) / 2) - 1).isEqualTo(3);
        assertThat(Math.round(Math.sqrt(81) / 2) - 1).isEqualTo(4);

    }
}
