package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

  public static String handle(ArgsName argsName) throws Exception {
        Path file = Paths.get(argsName.get("path"));
        List<String> fileValues = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        List<String> text = new ArrayList<>();

        try (var lines = new Scanner(file).useDelimiter(System.lineSeparator())) {
            var words = lines.useDelimiter(";|\\r\\n");
            while (words.hasNext()) {
                fileValues.add(words.next());
            }
        }
      System.out.println(fileValues.get(3));

        try (var filterValues = new Scanner(argsName.get("filter")).useDelimiter(",")) {
            while (filterValues.hasNext()) {
                indexes.add(fileValues.indexOf(filterValues.next()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Filter parameters or not exist or are not in line with the source data");
        }

        try (var lines = new Scanner(file).useDelimiter(System.lineSeparator())) {
            while (lines.hasNext()) {
                text.add(lines.next());
            }
        }

        String[][] wordsArray = new String[text.size()][indexes.size()];
        for (int row = 0; row < text.size(); row++) {
            List<String> parsedLine = new ArrayList<>();
            try (var y = new Scanner(text.get(row)).useDelimiter(argsName.get("delimiter"))) {
                while (y.hasNext()) {
                    parsedLine.add(y.next());
                }
                for (int column = 0; column < indexes.size(); column++) {
                    wordsArray[row][column] = parsedLine.get(indexes.get(column));
                }
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
        return collectedArray.toString();

    }

    public static void checkArgs(ArgsName argsName) {
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("The paths directory has wrong extension or does not exist");
        }
        if (argsName.get("delimiter").length() > 12) {
            throw new IllegalArgumentException("The delimiter should be one type");
        }
    }

    public static void writeOut(ArgsName argsName) throws Exception {
        if (argsName.get("out").endsWith("stdout")) {
            System.out.println(handle(argsName));
        } else {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(argsName.get("out"))
                    ))) {
                out.write(handle(argsName));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName rsl = ArgsName.of(args);
        checkArgs(rsl);
        writeOut(rsl);
    }
}