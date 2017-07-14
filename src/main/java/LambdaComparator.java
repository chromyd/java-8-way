import static java.util.Arrays.asList;

import java.util.List;

public class LambdaComparator {

    public static void main(String[] args)
    {
        List<Integer> naturals = asList(5, 10, 16, 3, 9, 12, 8, 19, 14, 15, 11, 6, 1, 2, 18, 13, 4, 7, 17, 20);
        List<Integer> integers = asList(9, -9, 0, 5, -5, 4, 7, 1, -3, -8, -7, -2, -6, -4, -10, 8, 2, -1, 3, 6, 10);

        naturals.sort(null);
        integers.sort(null);
        System.out.println(naturals);
        System.out.println(integers);
    }

}
