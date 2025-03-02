package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("."), new DuplicatesVisitor());
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        System.out.println(visitor.getFile());
    }
}