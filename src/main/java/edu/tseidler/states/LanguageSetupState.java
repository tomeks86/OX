package edu.tseidler.states;

import edu.tseidler.model.Language;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("CHOOSE_LANGUAGE"));
        String choice = input.get();
        while (!OXGame.available.contains(choice)) {
            if (choice.isEmpty()) break;
            output.accept(Language.get("CHOOSE_LANGUAGE"));
            choice = input.get();
        }
        lang = OXGame.loadLanguage(choice);
        output.accept(lang.toString() + " (" + Language.get("SELECTED") +")\n");
        return new BoardSetUpState(this);
    }

}
