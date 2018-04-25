package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StartState implements GameState {
    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("WELCOME"));
        output.accept(lang.get("SETUP"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        if (inputSupplier.get().equalsIgnoreCase(lang.get("YES")))
            return new LanguageSetupState(lang);
        else
            return null; // todo running state
    }

}
