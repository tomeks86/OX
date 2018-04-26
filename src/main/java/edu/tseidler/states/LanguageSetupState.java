package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("CHOOSE_LANGUAGE"));
        lang = switchLang(input.get());
        return new BoardSetUpState(this);
    }

    private Language switchLang(String languageShort) {
        if (!languageShort.isEmpty() && ("pl".equalsIgnoreCase(languageShort) || "en".equalsIgnoreCase(languageShort)))
            return new Language(languageShort);
        return this.lang;
    }
}
