package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArgsName validate(String[] args) {
        ArgsName argsToCheck = ArgsName.of(args);
        if (argsToCheck.get("d").isEmpty()) {
            throw new IllegalArgumentException("The directory value is not mentioned");
        }
        if (Files.isDirectory(Paths.get(argsToCheck.get("d")))) {
            throw new IllegalArgumentException(String.format("%s directory is not exist", argsToCheck.get("d")));
        }
        if (argsToCheck.get("e").isEmpty() || !argsToCheck.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The excluded file extension is not exist");
        }
        if (argsToCheck.get("o").isEmpty() || !argsToCheck.get("o").contains(".zip")) {
            throw new IllegalArgumentException("The target file is not mentioned");
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        validate(args);
        ArgsName argsParams = ArgsName.of(args);
        Path dir = Paths.get(argsParams.get("d"));
        zip.packFiles(
                Search.search(dir, q -> !q.toFile().getName().endsWith(argsParams.get("e"))),
                Paths.get(argsParams.get("o")));
    }
}