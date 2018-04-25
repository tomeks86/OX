package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {
    private Supplier<String> inputSupplier;
    private Consumer<String> output;
    private GameState currentState;
    private Map<String, String> lang;

    public OXGame(Supplier<String> inputSupplier, Consumer<String> output, String selLang) {
        this.inputSupplier = inputSupplier;
        this.output = output;
        this.lang = LanguageSelector.selectLanguage(selLang);
        this.currentState = new StartState();
    }

    public void start() {
        while (true) {
            doOneCycle();
        }
    }

    private void doOneCycle() {
        currentState.printTo(output, lang);
        this.currentState = currentState.getNextState(inputSupplier, lang);
    }


}
