package ru.job4j.serialization.json;

public class Seller {
    private final String name;

    public Seller(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seller{"
                + "name='"
                + name
                + '\''
                + '}';
    }

    public String getName() {
        return name;
    }
}