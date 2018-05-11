package edu.tseidler;

import edu.tseidler.service.FileLineConsumer;
import edu.tseidler.service.FileLineSupplier;
import edu.tseidler.states.OXGame;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

    private static final Supplier<String> DEFAULT_STRING_SUPPLIER = new Scanner(System.in)::nextLine;
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final Consumer<String> DEFAULT_STRING_CONSUMER = System.out::println;
    private static final Consumer<String> ERROR_CONSUMER = System.err::println;

    public static void main(String[] args) {

        final Properties defaultProperties = new Properties();
        defaultProperties.setProperty("input", "stdin");
        defaultProperties.setProperty("output", "stdout");
        defaultProperties.setProperty("language", "en");
        defaultProperties.setProperty("player1_name", "Jacek");
        defaultProperties.setProperty("player1_mark", "X");
        defaultProperties.setProperty("player1_first", "true");
        defaultProperties.setProperty("player2_name", "Placek");
        defaultProperties.setProperty("player2_mark", "O");
        defaultProperties.setProperty("player2_first", "false");
        defaultProperties.setProperty("board_rows", "3");
        defaultProperties.setProperty("board_cols", "3");
        defaultProperties.setProperty("board_winn", "3");


        Properties properties = readProperties();

        Supplier<String> inputSupplier = getStringSupplier(properties);

        Consumer<String> output = getStringConsumer(properties);

        new OXGame(inputSupplier, output, "en").start();
    }

    private static Consumer<String> getStringConsumer(Map<Object, Object> properties) {
        Map<String, Consumer<String>> consumerMap = new HashMap<String, Consumer<String>>() {{
            put("stdout", DEFAULT_STRING_CONSUMER);
            put("stderr", ERROR_CONSUMER);
            try {
                Path path = Paths.get((String) properties.getOrDefault("output_file", "output.log"));
                put("file", new FileLineConsumer(path));
            } catch (IOException e) {
                logger.log(Level.WARN, "file operation error - falling back to console output");
                put("file", DEFAULT_STRING_CONSUMER);
            }
        }};

        return consumerMap.get(properties.get("output"));
    }

    private static Supplier<String> getStringSupplier(Properties properties) {
        Map<String, Supplier<String>> supplierMap = new HashMap<String, Supplier<String>>() {{
            put("stdin", DEFAULT_STRING_SUPPLIER);
            try {
                put("file", new FileLineSupplier(Main.class.getResource((String) properties.getOrDefault("input_file", "scenarios/error")).getPath()));
            } catch (NullPointerException e) {
                logger.log(Level.WARN, "input file in settings.properties doesn't exist - falling back to console input");
                put("file", DEFAULT_STRING_SUPPLIER);
            }
        }};
        return supplierMap.getOrDefault(properties.get("input"), DEFAULT_STRING_SUPPLIER);
    }

    private static Properties readProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = Main.class.getResourceAsStream("settings.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
