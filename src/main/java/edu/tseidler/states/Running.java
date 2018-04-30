package edu.tseidler.states;

import edu.tseidler.model.*;
import edu.tseidler.service.InputParser;
import edu.tseidler.service.WinnerValidator;

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
        if (GameState.gamesPlayed == 3)
            return new GameOverState(this);
        while (!marked && !board.ifFull()) {
            output.accept(Language.build("PLAYER " + currentPlayer.getName() + " NEXT_MOVE " + currentPlayer.getMark()));
            choice = InputParser.parsePlayerMarkInput(input.get());
            marked = board.put(choice, currentPlayer.getMark());
            if (board.doWeHaveAWinner())
                return new WinnerState(this, currentPlayer);
        }
        output.accept(Language.build("PLAYER PUT " + currentPlayer.getMark() + " ON FIELD") + " : " + choice);
        if (!board.ifFull())
            return new Running(this);
        else
            return new DrawState(this);
    }
}
