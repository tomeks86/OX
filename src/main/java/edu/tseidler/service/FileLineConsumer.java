package edu.tseidler.service;

import edu.tseidler.Main;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

public class FileLineConsumer implements Consumer<String> {
    private final Path path;
    private static final Logger logger = Logger.getLogger(Main.class);

    public FileLineConsumer(Path path) throws IOException {
        this.path = path;
        if (Files.exists(path)) {
            try (BufferedWriter out = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)) {
                out.write("-------\n");
            }
        } else {
            try (BufferedWriter out = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW)) {
                out.write("-------\n");
            }
        }
    }

    @Override
    public void accept(String output) {
        try (BufferedWriter out = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            out.write(escapeColors(output));
        } catch (IOException e) {
            logger.log(Level.ERROR, "unable to write to file: " + path + "; message: " + output);
            e.printStackTrace();
        }
    }

    private String escapeColors(String output) {
        return output.replaceAll("\\u001B\\[[;\\d]*m", "");
    }
}
