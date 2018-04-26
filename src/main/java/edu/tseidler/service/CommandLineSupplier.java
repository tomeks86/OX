package edu.tseidler.service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLineSupplier implements Supplier<String> {
    List<String> lines;

    public CommandLineSupplier(Stream<String> supplier) {
        lines = supplier.collect(Collectors.toList());
    }

    @Override
    public String get() {
        if (!lines.isEmpty())
            return lines.remove(0);
        else return "";
    }
}
