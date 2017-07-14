package de.blogspot.soahowto.java8way.quiz;

public class InterfaceQuiz3 {
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

    public static void main(String[] args) {
        System.out.println(new I().foo());
    }
}
