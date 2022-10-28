package ru.job4j.io.fileSearchExam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class SearchFilesByCriteria {

    public static List<Path> search(ArgsName argsName) throws IOException {
        Predicate<Path> condition = new Predicate<Path>() {
            @Override
            public boolean test(Path path) {
                return false;
            }
        }
        SearchFiles searcher = new SearchFiles();
        if ("name".equals(argsName.get("t"))) {
            Predicate<Path> condition = p -> p.toFile().getName().equals(argsName.get("n"));
            SearchFiles searcher = new SearchFiles(condition);
        }
        if ("mask".equals(argsName.get("t"))) {
            Predicate<Path> condition = p -> p.toFile().getName().contains(argsName.get("n"));
            SearchFiles searcher = new SearchFiles(condition);
        }
        /*if ("regex".equals(argsName.get("t"))) {
            Predicate<Path> condition = p -> p.toFile().getName().matches()
        }*/
        Files.walkFileTree(argsName.get("d"), searcher);
    }
}
