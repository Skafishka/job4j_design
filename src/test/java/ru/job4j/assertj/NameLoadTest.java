package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyArray() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkContainSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "name";
        assertThatThrownBy(() -> nameLoad.parse((name)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the symbol");
    }

    @Test
    void checkContainKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=name";
        assertThatThrownBy(() -> nameLoad.parse((name)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a key");
    }

    @Test
    void checkContainValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "name=";
        assertThatThrownBy(() -> nameLoad.parse((name)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a value");
    }
}