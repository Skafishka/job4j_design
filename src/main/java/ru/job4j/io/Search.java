package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("In the root folder has no two arguments. Usage ROOT_FOLDER");
        }
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("Paths directory is not exist");
        }
        if (args[1].contains(".")) {
            throw new IllegalArgumentException("File extension is not correct");
        }
    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(".properties")).forEach(System.out::println);
    }
}
