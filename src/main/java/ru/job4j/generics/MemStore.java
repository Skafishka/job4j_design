package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T findById(String id) {
        if (storage.get(id) != null) {
            return storage.get(id);
        } else {
            return null;
        }
    }
}
