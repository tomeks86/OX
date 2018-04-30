package edu.tseidler.states;

import edu.tseidler.model.Language;

public class DrawState extends GameState {
    public DrawState(GameState previous) {
        super(previous);
    }

    @Override
    GameState getNextState() {
        output.accept(Language.build("DRAW"));
        output.accept(board.draw());
        board.clear();
        players.getNext().draw();
        players.getNext().draw();
        GameState.gamesPlayed++;
        return new Running(this);
    }
}
