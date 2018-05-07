package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardParameters;
import edu.tseidler.model.Language;
import edu.tseidler.service.InputParser;

import java.util.Arrays;

public class BoardSetUpState extends GameState {
    BoardSetUpState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("BOARD_SETUP"));
        output.accept(Language.get("BOARD_SIZE_FORMAT"));
        int[] boardDimensions = InputParser.parseBoardSize(input.get());
        output.accept(Language.get("UNDERSTOOD") + " " + Arrays.toString(boardDimensions));
        this.board = new Board(new BoardParameters(boardDimensions[0], boardDimensions[1], boardDimensions[2]));
        output.accept(board.toString() + "\n");
        return new PlayerSetUpState(this);
    }

}
