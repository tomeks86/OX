package edu.tseidler.states;

import edu.tseidler.model.Language;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {
    private Supplier<String> inputSupplier;
    private Consumer<String> output;
    private GameState currentState;
    private Language lang;
    public static int gamesPlayed = 0;

    public OXGame(Supplier<String> inputSupplier, Consumer<String> output, String selLang) {
        this.inputSupplier = inputSupplier;
        this.output = output;
        this.lang = new Language("en");
        this.currentState = new StartState();
    }

    public void start() {
        while (gamesPlayed < 3) {
            doOneCycle();
        }
    }

    private void doOneCycle() {
        currentState.printTo(output, lang);
        this.currentState = currentState.getNextState(inputSupplier, lang);
    }


}
