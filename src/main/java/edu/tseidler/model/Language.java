package edu.tseidler.model;

import edu.tseidler.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Language {
    final String name;
    static Map<String, String> wordMap;

    public Language(String name) {
        this.name = name;
        wordMap = new HashMap<>();
        Stream<String> lines = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(name + ".dat"))).lines();
        lines.forEach(line -> wordMap.putIfAbsent(line.split("\\s+")[0], line.split("\\s+", 2)[1]));
    }

    public static String get(String command) {
        String undefined = wordMap.getOrDefault("UNDEFINED", "[UNDEFINED] not found in dictionary");
        return wordMap.getOrDefault(command, command + " " + undefined);
    }

    public static String build(String input) {
        StringBuilder sb = new StringBuilder();
        for (String token : input.split("\\s+")) {
            if (isUpper(token)) {
                sb.append(Language.get(token));
            } else {
                sb.append(token);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private static boolean isUpper(String token) {
        return token.toUpperCase().equals(token);
    }

    @Override
    public String toString() {
        return get("LANGUAGE") + ": " + name;
    }
}
