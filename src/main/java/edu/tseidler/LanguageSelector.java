package edu.tseidler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LanguageSelector {

    public static  Map<String,String> selectLanguage(String selLang) {
        Map<String, String> lang = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/"+ selLang + ".dat"))) {
//        try (Stream<String> lines = Files.lines(Paths.get(getClass().getResource("en.dat").getPath()))) {
            lines.forEach(line -> lang.putIfAbsent(line.split("\\s+")[0], line.split("\\s+", 2)[1]));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lang;
    }
}
