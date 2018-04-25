package edu.tseidler;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        Supplier<String> inputSupplier;
        if (args.length > 0 && args[0].equals("-i")) {
             inputSupplier= new Scanner(System.in)::nextLine;
        } else {
            inputSupplier = new CommandLineSupplier(Arrays.stream(args).skip(1));
        }

        Consumer<String> output = System.out::println;

        new OXGame(inputSupplier, output, "en").start();
    }
}
