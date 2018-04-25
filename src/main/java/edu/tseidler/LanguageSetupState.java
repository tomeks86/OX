package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class LanguageSetupState implements GameState {
    private final Map<String, String> lang;

    public LanguageSetupState(Map<String, String> lang) {
        this.lang = lang;
    }

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("CHOOSE_LANGUAGE"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        lang = switchLang(inputSupplier.get());
        return new BoardSetUpState();
    }

    private Map<String,String> switchLang(String s) {
        if ("en pl".contains(s))
            return LanguageSelector.selectLanguage(s);
        return lang;
    }

}
