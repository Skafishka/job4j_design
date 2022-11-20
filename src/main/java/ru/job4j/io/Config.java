package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(q -> q.length() > 0 && q.charAt(0) != '#')
                    .forEach(q -> {
                        String[] string = q.split("=", 2);
                        if (q.startsWith("=") || (!q.contains("=")) || string[1].isEmpty()) {
                            throw new IllegalArgumentException(String.format("Incorrect line %s", q));
                        }
                        values.put(string[0], string[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("mistake in key=value");
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./src/main/resources/app.properties"));
    }
}
