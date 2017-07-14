package de.blogspot.soahowto.java8way.quiz;

public class InterfaceQuiz1 {
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

    public static void main(String[] args) {
        System.out.println(new C().foo());
    }
}
