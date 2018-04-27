package edu.tseidler.model;

import edu.tseidler.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Language {
    final String name;
    Map<String, String> wordMap;

    public Language(String name) {
        this.name = name;
        this.wordMap = new HashMap<>();
        Stream<String> lines = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(name + ".dat"))).lines();
        lines.forEach(line -> wordMap.putIfAbsent(line.split("\\s+")[0], line.split("\\s+", 2)[1]));
    }

    public String get(String command) {
        return this.wordMap.getOrDefault(command, command + " [not defined in the language file]");
    }

    @Override
    public String toString() {
        return get("LANGUAGE") + ": " + name;
    }
}
