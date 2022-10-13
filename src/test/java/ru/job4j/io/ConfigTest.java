package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithComment() {
        String path = ".data/test1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Sergei Kolygin");
    }

    @Test
    void whenDoubleEqualSign() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String path = ".data/test1.properties";
                    Config config = new Config(path);
                    config.load();
                    config.value("surname=");
                }
        );
    }
}