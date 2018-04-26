package edu.tseidler.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLineSupplier implements Supplier<String> {
    List<String> lines = new LinkedList<>();

    public FileLineSupplier(String path) {
        try (Stream<String> file_lines = Files.lines(Paths.get(path))) {
            lines = file_lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get() {
        if (!lines.isEmpty())
            return lines.remove(0);
        else return " ";
    }
}
