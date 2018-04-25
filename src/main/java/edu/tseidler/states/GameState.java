package edu.tseidler.states;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {
    void printTo(Consumer<String> output, Map<String, String> lang);

    GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang);
}
