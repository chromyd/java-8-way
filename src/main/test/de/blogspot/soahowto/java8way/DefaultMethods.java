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

    @Test
    public void classesOverInterfaces() {
        assertThat(new C().foo()).isEqualTo("B");
    }

    @Test
    public void specificOverGeneric() {
        assertThat(new I().foo()).isEqualTo("H");
    }

    public static interface A {
        default String foo() {
            return "A";
        }
    }

    public static class B {
        public String foo() {
            return "B";
        }
    }

    public static class C extends B implements A {
    }

    public static interface D {
        default String foo() {
            return "D";
        }
    }

    public static interface E {
        default String foo() {
            return "E";
        }
    }

    public static class F implements D, E {
        public String foo() {
            return D.super.foo();
        }
    }

    public static interface G {
        default String foo() {
            return "G";
        }
    }

    public static interface H extends G {
        default String foo() {
            return "H";
        }
    }

    public static class I implements G, H {
    }

}
