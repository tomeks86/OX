package edu.tseidler.states;

public class SetupSummaryState extends GameState {
    SetupSummaryState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("SUMMARY"));
        output.accept(lang.toString());
        output.accept(board.toString());
        output.accept(players.toString());
        return new Running(this);
    }

}
