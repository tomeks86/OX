package edu.tseidler.states;

import edu.tseidler.model.Language;

public class SetupSummaryState extends GameState {
    SetupSummaryState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("SUMMARY") + "\n");
        output.accept(lang.toString());
        output.accept(board.toString());
        output.accept(players.toString() + "\n");
        output.accept(Language.build("_TO_ _QUIT_ _ENTER_ : _QUIT_"));
        output.accept("\n");
        return new Running(this);
    }

}
