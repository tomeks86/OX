package edu.tseidler.states;

import edu.tseidler.model.Language;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("CHOOSE_LANGUAGE"));
        switchLang(input.get());
        output.accept(lang.toString() + " (" + Language.get("SELECTED") +")\n");
        return new BoardSetUpState(this);
    }

    private void switchLang(String languageAbbrev) {
        if (!languageAbbrev.isEmpty() && ("pl".equalsIgnoreCase(languageAbbrev) || "en".equalsIgnoreCase(languageAbbrev)))
            lang = new Language(languageAbbrev);
    }
}
