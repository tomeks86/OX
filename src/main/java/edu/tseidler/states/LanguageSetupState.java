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
        output.accept(Language.get("CHOOSE_LANGUAGE"));
        switchLang(input.get());
        return new BoardSetUpState(this);
    }

    private void switchLang(String languageAbbrev) {
        if (!languageAbbrev.isEmpty() && ("pl".equalsIgnoreCase(languageAbbrev) || "en".equalsIgnoreCase(languageAbbrev)))
            new Language(languageAbbrev);
    }
}
