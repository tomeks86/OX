package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.Coordinates;

class Running extends GameState {

    public Running(GameState previousState) {
        super(previousState);
    }

    @Override
    GameState getNextState() {
        output.accept(board.draw());
        return new GameOverState(this);
    }
}
