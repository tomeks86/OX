package edu.tseidler.states;

import edu.tseidler.model.Language;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        String choice;
        choice = getLanguageChoice();
        while (!OXGame.availableLangShorts.contains(choice)) {
            if (choice.isEmpty()) break;
            choice = getLanguageChoice();
        }
        lang = OXGame.loadLanguage(choice);
        output.accept(lang.toString() + " (" + Language.get("SELECTED") +")\n");
        return new BoardSetUpState(this);
    }

    private String getLanguageChoice() {
        String choice;
        output.accept(Language.get("CHOOSE_LANGUAGE"));
        choice = input.get();
        return choice;
    }

}
