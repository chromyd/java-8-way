import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class StreamSandbox {

    public static void main(String[] args) {

        Instant begin = Instant.now();
        Stream.iterate(BigDecimal.ONE, null).limit(10).forEach(System.out::println);

        System.out.println("Elapsed time: " + Duration.between(begin, Instant.now()));

    }
}
