package edu.tseidler;

import edu.tseidler.service.CommandLineSupplier;
import edu.tseidler.service.FileLineSupplier;
import edu.tseidler.states.OXGame;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        Supplier<String> inputSupplier = new Scanner(System.in)::nextLine;
        if (args.length > 0 && args[0].equalsIgnoreCase("-c")) {
            inputSupplier = new CommandLineSupplier(Arrays.stream(args).skip(1));
        } else if (args.length > 1 && args[0].equalsIgnoreCase("-f")) {
            inputSupplier = new FileLineSupplier(args[1]);
        }

        Consumer<String> output = System.out::println;

        new OXGame(inputSupplier, output, "en").start();
    }
}
