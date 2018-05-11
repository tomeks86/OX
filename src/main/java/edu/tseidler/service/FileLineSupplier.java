package edu.tseidler.service;

import edu.tseidler.Main;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLineSupplier implements Supplier<String> {
    List<String> lines = new LinkedList<>();
    Scanner scanner;
    boolean finished = false;
    private static final Logger logger = Logger.getLogger(Main.class);

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
        else {
            if (!finished) {
                logger.log(Level.INFO, "file finished switching to console input");
                scanner = new Scanner(System.in);
                finished = true;
            }
            lines.add(scanner.nextLine());
            return lines.remove(0);
        }
    }
}
