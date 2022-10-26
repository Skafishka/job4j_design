package ru.job4j.serialization.java;

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
}
