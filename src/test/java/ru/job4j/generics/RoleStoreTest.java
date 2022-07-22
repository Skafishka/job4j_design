package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RoleStoreTest {

    @Test
    void whenAddAndDeleteAndFindThenUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Aleks", "Boss"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergei", "CEO"));
        store.add(new Role("89", "Mark", "Executor"));
        store.add(new Role("45", "Maks", "Curator"));
        store.replace("89", new Role("45", "Grigory", "Deputy of chief"));
        Role result = store.findById("45");
        assertThat(result.getExecutionRole()).isEqualTo("Curator");
    }

}