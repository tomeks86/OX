package edu.tseidler;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {
    private Supplier<String> inputSupplier;
    private Consumer<String> output;
    private GameState currentState;

    public OXGame(Supplier<String> inputSupplier, Consumer<String> output) {
        this.inputSupplier = inputSupplier;
        this.output = output;
        this.currentState = new SetUp();
    }

    public void start() {
        while (true) {
            doOneCycle();
        }
    }

    private void doOneCycle() {
        currentState.printTo(output);
        this.currentState = currentState.getNextState();
    }


}
