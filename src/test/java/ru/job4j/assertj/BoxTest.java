package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isMixedCase();
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .doesNotContainAnyWhitespaces()
                .isMixedCase();
    }

    @Test
    void isGetNumberOfVerticesMinusOne() {
        Box box = new Box(4, -1);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1)
                .isOdd()
                .isGreaterThan(-2);
    }

    @Test
    void isGetNumberOfVerticesWhenUnknown() {
        Box box = new Box(3, 4);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1)
                .isNegative()
                .isCloseTo(-1, Percentage.withPercentage(10));
    }

    @Test
    void isExistTrue() {
        Box box = new Box(4, 4);
        boolean check = box.isExist();
        assertThat(check).isTrue()
                .isNotNull();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(4, -1);
        boolean check = box.isExist();
        assertThat(check).isFalse();
    }

    @Test
    void isGetAreaCase4() {
        Box box = new Box(4, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(15.58d, withPrecision(0.06d))
                .isCloseTo(15.58d, withPrecision(0.06d));
    }

    @Test
    void isGetAreaCase8() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24d)
                .isGreaterThan(20d)
                .isLessThan(30d);
    }
}