package edu.tseidler.states;

public class SetupSummaryState extends GameState {
    SetupSummaryState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("SUMMARY"));
        output.accept(lang.toString());
        output.accept(board.present(lang));
        output.accept(players.present(lang));
        return new Running(this);
    }

}
