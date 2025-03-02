package ru.job4j.set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenContainsNonNull() {
        Set<Integer> set = new SimpleSet<>();
        set.add(4);
        set.add(2);
        set.add(1);
        assertThat(set.contains(7)).isFalse();
    }

    @Test
    void whenContainsNull() {
        Set<Integer> set = new SimpleSet<>();
        set.add(4);
        set.add(null);
        set.add(2);
        set.add(null);
        assertThat(set.contains(null)).isTrue();
    }
}