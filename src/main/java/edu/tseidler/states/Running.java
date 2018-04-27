package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.Coordinates;
import edu.tseidler.model.Player;
import edu.tseidler.service.InputParser;

class Running extends GameState {

    public Running(GameState previousState) {
        super(previousState);
    }

    @Override
    GameState getNextState() {
        output.accept(board.draw());
        Player currentPlayer = players.getNext();
        boolean marked = false;
        int choice = -1;
        while (!marked && !board.ifFull()) {
            output.accept(lang.get("PLAYER") + " " + currentPlayer.name + " " + lang.get("NEXT_MOVE") + " " + currentPlayer.mark);
            choice = InputParser.parsePlayerMarkInput(input.get());
            marked = board.put(choice, currentPlayer.mark);
        }
        output.accept(lang.get("PLAYER" + " " + lang.get("PUT") + " " + currentPlayer.mark + " " + lang.get("ON") + " " + lang.get("FIELD") + ": " + choice));
        if (!board.ifFull())
            return new Running(this);
        else
            return new GameOverState(this);
    }
}
