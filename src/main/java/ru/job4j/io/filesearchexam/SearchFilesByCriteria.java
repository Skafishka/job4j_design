package ru.job4j.io.filesearchexam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class SearchFilesByCriteria {

    public static void search(ArgsName argsName) throws IOException {
        Predicate<Path> condition = null;
        if ("name".equals(argsName.get("t"))) {
            condition = p -> p.toFile().getName().equals(argsName.get("n"));
        }
        if ("mask".equals(argsName.get("t"))) {
            var string = argsName.get("n");
            string = string.replace(".", "[.]");
            string = string.replace("*", ".+");
            string = string.replace("?", ".");
            String finalString = string;
            condition = p -> p.toFile().getName().matches(finalString);
        }
        if ("regex".equals(argsName.get("t"))) {
            condition = p -> p.toFile().getName().matches(argsName.get("n"));
        }
        var searcher = new SearchFiles(condition);
        Files.walkFileTree(Path.of(argsName.get("d")), searcher);
        List<Path> pathList = searcher.getPaths();
        writeOut(argsName, pathList);
        check(argsName);
    }

    public static void writeOut(ArgsName argsName, List<Path> pathList) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("o"))
                ))) {
            pathList.forEach(out::println);
        }
    }

    public static void check(ArgsName argsName) {
        var path = Path.of(argsName.get("d"));
        var typeSearch = argsName.get("t");
        if (!"regex".equals(typeSearch) && !"mask".equals(typeSearch) && !"name".equals(typeSearch)) {
            throw new IllegalArgumentException("There is no right type-search");
        }
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root path is absent");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("There is not full search criteria filled");
        }
        search(ArgsName.of(args));
    }
}
