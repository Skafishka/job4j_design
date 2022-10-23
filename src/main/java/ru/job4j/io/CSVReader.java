package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static List<String> handle(ArgsName argsName) throws Exception {
        Path file = Paths.get(argsName.get("path"));
        List<String> fileValues = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        List<String> text = new ArrayList<>();

        try (var values = new Scanner(file).useDelimiter(";|\\r\\n")) {
            while (values.hasNext()) {
                fileValues.add(values.next());
            }
        }

        try (var filterValues = new Scanner(argsName.get("filter")).useDelimiter(",")) {
            while (filterValues.hasNext()) {
                indexes.add(fileValues.indexOf(filterValues.next()));
            }
        }

        try (var lines = new Scanner(file).useDelimiter(System.lineSeparator())) {
            while (lines.hasNext()) {
                text.add(lines.next());
            }
        }

        String[][] wordsArray = new String[text.size()][indexes.size()];
        for (int row = 0; row < text.size(); row++) {
            List<String> parsedLine = new ArrayList<>();
            var y = new Scanner(text.get(row)).useDelimiter(";");
            while (y.hasNext()) {
                parsedLine.add(y.next());
            }
            for (int column = 0; column < indexes.size(); column++) {
                    wordsArray[row][column] = parsedLine.get(indexes.get(column));
            }
        }

        StringBuilder collectedArray = new StringBuilder();
        for (String[] string : wordsArray) {
            StringJoiner collectedString = new StringJoiner(";", "", "");
            for (String word : string) {
                collectedString.add(word);
            }
            collectedArray.append(collectedString).append(System.lineSeparator());
        }
        String rsl = collectedArray.toString();

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            out.write(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void checkArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("In the root folder has no four arguments. Usage ROOT_FOLDER");
        }
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("Paths directory is not exist");
        }
        if (args[1].isEmpty()) {
            throw new IllegalArgumentException("File extension is absent");
        }
        if (args[2].isEmpty()) {
            throw new IllegalArgumentException("Out directory is not exist");
        }
        if (args[3].isEmpty()) {
            throw new IllegalArgumentException("Filter parameters are absent");
        }
    }

    public static void main(String[] args) throws Exception {
        checkArgs(args);
        ArgsName rsl = ArgsName.of(args);
        handle(rsl);
    }

}