package de.blogspot.soahowto.java8way;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DefaultMethods {

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

    public static interface E extends D {
        default String foo() {
            return "E";
        }
    }

    public static class F implements D, E {

    }

    public static interface G {
        default String foo() {
            return "G";
        }
    }

    public static interface H {
        default String foo() {
            return "H";
        }
    }

    public static class I implements G, H {

        public String foo() {
            return H.super.foo();
        }
    }

    @Test
    public void classesOverInterfaces() {
        assertThat(new C().foo()).isEqualTo("B");
    }

    @Test
    public void specificOverGeneric() {
        assertThat(new F().foo()).isEqualTo("E");
    }

    @Test
    public void explicit() {
        assertThat(new I().foo()).isEqualTo("H");
    }

}
