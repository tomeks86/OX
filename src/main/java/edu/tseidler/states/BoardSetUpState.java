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
        BoardParameters parameters = new BoardParameters(boardDimensions[0], boardDimensions[1], boardDimensions[2]);
//        boolean accepted = false;
//        while (parameters.isChanged() && !accepted) {
//            this.board = new Board(parameters);
//            output.accept(board.toString() + "\n");
//            output.accept(Language.get("RETRY"));
//            accepted = InputParser.acceptanceInput(input.get());
//            if (!accepted) break;
//            else {
//                boardDimensions = InputParser.parseBoardSize(input.get());
//                output.accept(Language.get("UNDERSTOOD") + " " + Arrays.toString(boardDimensions));
//                parameters = new BoardParameters(boardDimensions[0], boardDimensions[1], boardDimensions[2]);
//                this.board = new Board(parameters);
//                output.accept(board.toString() + "\n");
//            }
//        }
        this.board = new Board(parameters);
        output.accept(board.toString() + "\n");
        return new PlayerSetUpState(this);
    }

}
