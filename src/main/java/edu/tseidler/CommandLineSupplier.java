package edu.tseidler;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CommandLineSupplier implements Supplier<String> {
    private final Stream<String> supplier;

    public CommandLineSupplier(Stream<String> supplier) {
        this.supplier = supplier;
    }

    @Override
    public String get() {
        return supplier.findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
