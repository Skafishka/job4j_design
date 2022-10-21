package ru.job4j.io;

import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        File file = new File(path);
        var data = "";
        List<String> values = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            in.readLine();
        }
        try (var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(argsName.get("delimiter"))) {
            while (scanner.hasNext()) {
                try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
                    for (Scanner it = scanner; it.hasNext();) {
                        String tmpString = it.next();
                        out.append(tmpString.substring(tmpString.indexOf(argsName.get("filter"))))
                                .append(lineSeparator());
                    }
                    System.out.println(out);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName q = ArgsName.of(args);
        handle(q);
    }
}