package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.service.InputParser;

public class BoardSetUpState extends GameState {
    BoardSetUpState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("BOARD_SETUP"));
        output.accept(Language.get("BOARD_SIZE_FORMAT"));
        int[] boardDimensions = InputParser.parseBoardSize(input.get());
        this.board = new Board(boardDimensions);
        return new PlayerSetUpState(this);
    }

}
