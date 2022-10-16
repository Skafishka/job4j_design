package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C:/projects");
        if(!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("No directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        for (File subFile :  file.listFiles()) {
            System.out.printf("file %s has size: %s%n", subFile.getAbsoluteFile().getName(), subFile.getAbsoluteFile().length());
        }
    }
}
