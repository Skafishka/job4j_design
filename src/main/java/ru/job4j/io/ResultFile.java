package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        int[] x = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int q : x) {
                for (int w = 0; w < 11; w++) {
                    out.write(((q * w) + " ").getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
