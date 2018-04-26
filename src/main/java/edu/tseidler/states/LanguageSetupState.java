package edu.tseidler.states;

import edu.tseidler.model.Language;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LanguageSetupState implements GameState {
    private Language lang;

    public LanguageSetupState(Language lang) {
        this.lang = lang;
    }

    @Override
    public void printTo(Consumer<String> output, Language lang) {
        output.accept(lang.get("CHOOSE_LANGUAGE"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Language lang) {
        this.lang = switchLang(inputSupplier.get());
        return new BoardSetUpState(this.lang);
    }

    private Language switchLang(String languageShort) {
        if ("pl".contains(languageShort))
            return new Language(languageShort);
        return this.lang;
    }
}
