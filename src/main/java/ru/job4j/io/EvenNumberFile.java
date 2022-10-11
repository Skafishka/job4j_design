package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        int[] q = new int[]{1, 6, 14, 17};
        try (FileOutputStream out = new FileOutputStream("even.txt")) {
            for (int w : q) {
                out.write((w + "").getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " is even");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
