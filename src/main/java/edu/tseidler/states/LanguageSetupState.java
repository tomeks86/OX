package edu.tseidler.states;

import edu.tseidler.LanguageSelector;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LanguageSetupState implements GameState {
    private Map<String, String> lang;

    public LanguageSetupState(Map<String, String> lang) {
        this.lang = lang;
    }

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("CHOOSE_LANGUAGE"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        this.lang = switchLang(inputSupplier.get());
        return new BoardSetUpState(lang);
    }

    private Map<String,String> switchLang(String s) {
        if ("en pl".contains(s))
            return LanguageSelector.selectLanguage(s);
        return lang;
    }

}
