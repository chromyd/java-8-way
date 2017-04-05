package de.blogspot.soahowto.java8way;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DefaultMethods {

    @Test
    public void sortNatural() {
        List<Integer> a = Arrays.asList(11, 23, 5, 16, 1, 18, 99, 42);

        a.sort(naturalOrder());
        System.out.println("Sorted:" + a);

        assertThat(a).isSorted();

    }

    @Test
    public void sortReverse() {
        List<Integer> a = Arrays.asList(11, 23, 5, 16, 1, 18, 99, 42);

        a.sort(reverseOrder());
        System.out.println("Reverse Sorted:" + a);
        assertThat(a).isSortedAccordingTo(reverseOrder());

    }

}
