package edu.tseidler.states;

import edu.tseidler.model.Language;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        String choice = getLanguageChoice();
        while (!OXGame.availableLangShorts.contains(choice)) {
            if (choice.isEmpty()) break;
            return new LanguageSetupState(this);
        }
        lang = OXGame.loadLanguage(choice);
        output.accept(Language.build("_SELECTED_ " + lang.toString()) + "\n");
        return new BoardSetUpState(this);
    }

    private String getLanguageChoice() {
        String choice;
        output.accept(Language.get("CHOOSE_LANGUAGE"));
        choice = input.get();
        return choice;
    }

}
