package edu.tseidler.states;

import edu.tseidler.model.Language;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("CHOOSE_LANGUAGE"));
        lang = OXGame.loadLanguage(input.get());
        output.accept(lang.toString() + " (" + Language.get("SELECTED") +")\n");
        return new BoardSetUpState(this);
    }

}
