package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(args)
                .toList()
                .forEach(q -> {
                    int equalIndex = q.indexOf("=");
                    String[] w = q.split("=", 2);
                    String[] e = w[0].split("-", 2);
                    validate(q, equalIndex);
                    values.put(e[1], w[1]);
                });
    }

    private void validate(String line, int index) {
        if (line.indexOf("-") != 0) {
            throw new IllegalArgumentException();
        }
        if (index == -1) {
            throw new IllegalArgumentException();
        }
        if (index == 1) {
            throw new IllegalArgumentException();
        }
        if (index == line.length() - 1) {
            throw new IllegalArgumentException();
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
