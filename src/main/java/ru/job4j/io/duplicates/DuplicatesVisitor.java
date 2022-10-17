package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(attrs.size(), file.toFile().getName());
        files.putIfAbsent(property, new ArrayList<>());
        files.get(property).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public List<Path> getFile() {
        List<Path> doubling = new ArrayList<>();
        files.values().stream()
                .filter(q -> q.size() > 1)
                .forEach(doubling::addAll);
        return doubling;
    }
}