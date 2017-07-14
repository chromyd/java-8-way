import java.util.stream.Stream;

public class UnaryOpEvaluator {

    public static void main(String[] args) {
        Stream.iterate(1, n -> -n).limit(10).forEach(System.out::println);
    }

}
