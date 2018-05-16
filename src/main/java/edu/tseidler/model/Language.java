package edu.tseidler.model;

import edu.tseidler.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Language {
    private final String name;
    private static Map<String, String> wordMap;

    public Language(String name) {
        this.name = name;
        Stream<String> lines = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(name + ".dat"))).lines();
        wordMap = lines.map(line -> line.split("\\s+", 2))
                .collect(Collectors.toMap(l -> l[0], l -> l[1]));
    }

    public static String get(String command) {
        String undefined = wordMap.getOrDefault("UNDEFINED", "[UNDEFINED] not found in dictionary");
        return wordMap.getOrDefault(command, command + " " + undefined);
    }

    public static String build(String input) {
        StringBuilder sb = new StringBuilder();
        for (String token : input.split("\\s+")) {
            if (isDictionaryWord(token)) {
                sb.append(Language.get(token.substring(1, token.length() - 1)));
            } else {
                sb.append(token);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private static boolean isDictionaryWord(String token) {
        return (token.startsWith("_") && token.endsWith("_"));
    }

    @Override
    public String toString() {
        return get("LANGUAGE") + " " + name + "\n";
    }
}
