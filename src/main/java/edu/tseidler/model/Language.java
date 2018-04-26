package edu.tseidler.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Language {
    final String name;
    Map<String,String> wordMap;

    public Language(String name) {
        this.name = name;
        this.wordMap = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/"+ name + ".dat"))) {
//        try (Stream<String> lines = Files.lines(Paths.get(getClass().getResource("en.dat").getPath()))) {
            lines.forEach(line -> wordMap.putIfAbsent(line.split("\\s+")[0], line.split("\\s+", 2)[1]));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String get(String command) {
        return this.wordMap.getOrDefault(command, command + " [not defined in the language file]");
    }

    @Override
    public String toString() {
        return "language: " + name;
    }
}
