package ru.job4j.io;

import java.io.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import static java.lang.System.lineSeparator;

public class Analysis {

    private String rsl;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                boolean check = false;
                for (String tmpString = in.readLine(); tmpString != null; tmpString = in.readLine()) {
                    if (!check && tmpString.startsWith("500") || !check && tmpString.startsWith("400")) {
                        rsl = tmpString.substring(tmpString.indexOf(' ') + 1) + ";";
                        out.print(rsl);
                        check = true;
                    }
                    if (check && tmpString.startsWith("300") || tmpString.startsWith("200")) {
                        rsl = tmpString.substring(tmpString.indexOf(' ') + 1) + ";" + lineSeparator();
                        out.print(rsl);
                        check = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("/data/server.csv"))) {
            out.println("200 10:56:01");
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analysis q = new Analysis();
        q.unavailable("/data/server.csv", "/data/unavailable.csv");
    }
}