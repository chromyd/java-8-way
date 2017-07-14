package de.blogspot.soahowto.java8way.exercise.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

import org.junit.Test;

public class SimpleLambdaTest {

    private IntUnaryOperator magic = null;

    private IntUnaryOperator cool = null;

    private IntUnaryOperator tricky = null;

    private IntBinaryOperator sweet = null;

    private IntUnaryOperator crazy = null;

    private IntBinaryOperator insane = null;

    @Test
    public void testSingleStar1() {
        assertThat(magic.applyAsInt(0)).isEqualTo(0);
        assertThat(magic.applyAsInt(1)).isEqualTo(1);
        assertThat(magic.applyAsInt(2)).isEqualTo(4);
        assertThat(magic.applyAsInt(3)).isEqualTo(9);
        assertThat(magic.applyAsInt(4)).isEqualTo(16);
        assertThat(magic.applyAsInt(5)).isEqualTo(25);
    }

    @Test
    public void testSingleStar2() {
        assertThat(cool.applyAsInt(0)).isEqualTo(0);
        assertThat(cool.applyAsInt(1)).isEqualTo(1);
        assertThat(cool.applyAsInt(2)).isEqualTo(0);
        assertThat(cool.applyAsInt(3)).isEqualTo(3);
        assertThat(cool.applyAsInt(4)).isEqualTo(0);
        assertThat(cool.applyAsInt(5)).isEqualTo(5);
    }

    @Test
    public void testDoubleStar1() {
        assertThat(tricky.applyAsInt(0)).isEqualTo(0);
        assertThat(tricky.applyAsInt(1)).isEqualTo(0);
        assertThat(tricky.applyAsInt(2)).isEqualTo(2);
        assertThat(tricky.applyAsInt(3)).isEqualTo(6);
        assertThat(tricky.applyAsInt(4)).isEqualTo(12);
        assertThat(tricky.applyAsInt(5)).isEqualTo(20);
        assertThat(tricky.applyAsInt(6)).isEqualTo(30);
    }

    @Test
    public void testDoubleStar2() {
        assertThat(sweet.applyAsInt(1, 1)).isEqualTo(0);
        assertThat(sweet.applyAsInt(1, 2)).isEqualTo(1);
        assertThat(sweet.applyAsInt(1, 3)).isEqualTo(1);
        assertThat(sweet.applyAsInt(1, 4)).isEqualTo(1);
        assertThat(sweet.applyAsInt(2, 1)).isEqualTo(0);
        assertThat(sweet.applyAsInt(2, 2)).isEqualTo(0);
        assertThat(sweet.applyAsInt(2, 3)).isEqualTo(2);
        assertThat(sweet.applyAsInt(2, 4)).isEqualTo(2);
        assertThat(sweet.applyAsInt(3, 1)).isEqualTo(0);
        assertThat(sweet.applyAsInt(3, 2)).isEqualTo(1);
        assertThat(sweet.applyAsInt(3, 3)).isEqualTo(0);
        assertThat(sweet.applyAsInt(3, 4)).isEqualTo(3);
        assertThat(sweet.applyAsInt(4, 1)).isEqualTo(0);
        assertThat(sweet.applyAsInt(4, 2)).isEqualTo(0);
        assertThat(sweet.applyAsInt(4, 3)).isEqualTo(1);
        assertThat(sweet.applyAsInt(4, 4)).isEqualTo(0);

    }

    @Test
    public void testTripleStar1() {
        assertThat(crazy.applyAsInt(0)).isEqualTo(0);
        assertThat(crazy.applyAsInt(1)).isEqualTo(1);
        assertThat(crazy.applyAsInt(2)).isEqualTo(10);
        assertThat(crazy.applyAsInt(3)).isEqualTo(11);
        assertThat(crazy.applyAsInt(4)).isEqualTo(100);
        assertThat(crazy.applyAsInt(5)).isEqualTo(101);
        assertThat(crazy.applyAsInt(6)).isEqualTo(110);
        assertThat(crazy.applyAsInt(7)).isEqualTo(111);
    }

    @Test
    public void testTripleStar2() {
        assertThat(insane.applyAsInt(1, 1)).isEqualTo(0);
        assertThat(insane.applyAsInt(1, 2)).isEqualTo(-3);
        assertThat(insane.applyAsInt(1, 3)).isEqualTo(-8);
        assertThat(insane.applyAsInt(1, 4)).isEqualTo(-15);
        assertThat(insane.applyAsInt(2, 1)).isEqualTo(3);
        assertThat(insane.applyAsInt(2, 2)).isEqualTo(0);
        assertThat(insane.applyAsInt(2, 3)).isEqualTo(-5);
        assertThat(insane.applyAsInt(2, 4)).isEqualTo(-12);
        assertThat(insane.applyAsInt(3, 1)).isEqualTo(8);
        assertThat(insane.applyAsInt(3, 2)).isEqualTo(5);
        assertThat(insane.applyAsInt(3, 3)).isEqualTo(0);
        assertThat(insane.applyAsInt(3, 4)).isEqualTo(-7);
        assertThat(insane.applyAsInt(4, 1)).isEqualTo(15);
        assertThat(insane.applyAsInt(4, 2)).isEqualTo(12);
        assertThat(insane.applyAsInt(4, 3)).isEqualTo(7);
        assertThat(insane.applyAsInt(4, 4)).isEqualTo(0);
    }
}
