package edu.tseidler.states;

import edu.tseidler.model.Language;

public class DrawState extends GameState {
    public DrawState(GameState previous) {
        super(previous);
    }

    @Override
    GameState getNextState() {
        output.accept(board.draw());
        output.accept(Language.build("_DRAW_") + "\n");
        board.clear();
        players.getNext().draw();
        players.getNext().draw();
        players.switchStartingPlayer();
        GameState.gamesPlayed++;
        return new Running(this);
    }
}
