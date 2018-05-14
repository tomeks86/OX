package edu.tseidler.service;

import edu.tseidler.Main;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLineSupplier implements Supplier<String> {
    private List<String> lines = new LinkedList<>();
    private Scanner scanner;
    private boolean finished = false;
    private static final Logger logger = Logger.getLogger(Main.class);

    public FileLineSupplier(InputStream input_file) {
        lines = new BufferedReader(new InputStreamReader(input_file)).lines().collect(Collectors.toList());
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
