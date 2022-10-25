package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    static String rsl;

    public static void handle(ArgsName argsName) throws Exception {
        Path file = Paths.get(argsName.get("path"));
        List<String> fileValues = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        List<String> text = new ArrayList<>();

        List<String> fileValues1 = new ArrayList<>();
        String del = argsName.get("delimiter");
        try (var lines = new Scanner(file).useDelimiter(System.lineSeparator())) {
            while (lines.hasNext()) {
                fileValues1.add(lines.next());
            }
        }
        for (String d : fileValues1) {
            try (var v = new Scanner(d).useDelimiter(del)) {
                while (v.hasNext()) {
                    fileValues.add(v.next());
                }
            }
        }

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
        rsl = collectedArray.toString();

        checkArgs(argsName);
        writeOut(argsName, rsl);
    }

    public static void checkArgs(ArgsName argsName) {
        String del = argsName.get("delimiter");
        if (!":".equals(del) && !";".equals(del) && !"\r\n".equals(del)) {
            throw new IllegalArgumentException("Wrong delimiter format");
        }
        if (!argsName.get("path").endsWith(".csv") && Paths.get(argsName.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("The paths directory has wrong extension or does not exist");
        }
        if (!argsName.get("out").contains("stdout") && !argsName.get("out").contains(".csv")) {
            throw new IllegalArgumentException("The output format should be or stdout or with .csv extension");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("The filter value is empty");
        }
    }

    public static void writeOut(ArgsName argsName, String rsl) throws Exception {
        if (argsName.get("out").endsWith("stdout")) {
            System.out.println(rsl);
        } else {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(argsName.get("out"))
                    ))) {
                out.write(rsl);
            }
        }
    }

    public static void main(String[] args) throws Exception {
      if (args.length == 4) {
          handle(ArgsName.of(args));
      }
    }
}