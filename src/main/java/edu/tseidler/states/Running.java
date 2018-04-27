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
        board = new Board(new int[] {5, 15, 3});
        board.put(new Coordinates(2, 0), BoardField.X);
        board.put(new Coordinates(0, 5), BoardField.O);
        System.out.println((board.put(new Coordinates(6, 5), BoardField.O)));
        output.accept(board.draw());
        return new GameOverState(this);
    }
}
