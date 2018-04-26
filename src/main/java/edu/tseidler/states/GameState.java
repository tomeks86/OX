package edu.tseidler.states;

import edu.tseidler.model.Language;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {
    void printTo(Consumer<String> output, Language lang);

    GameState getNextState(Supplier<String> inputSupplier, Language lang);
}
