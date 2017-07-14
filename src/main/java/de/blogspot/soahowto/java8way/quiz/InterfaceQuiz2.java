package de.blogspot.soahowto.java8way.quiz;

public class InterfaceQuiz2 {
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

    public static void main(String[] args) {
        System.out.println(new F().foo());
    }
}
