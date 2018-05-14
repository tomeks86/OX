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
        this.board = new Board(parameters);
        if (parameters.isChanged()) {
            output.accept(Language.get("BOARD_PARAMETERS_CHANGED"));
            output.accept(board.toString()+ "\n");
            boolean accepted = false;
            while (!accepted) {
                output.accept(Language.build("_ACCEPT_ _YESORNO_"));
                String resp = input.get();
                while (Language.get("YES").equalsIgnoreCase(resp) && Language.get("NO").equalsIgnoreCase(resp)) {
                    output.accept(Language.build("_ACCEPT_ _YESORNO_"));
                    resp = input.get();
                }
                if (resp.equalsIgnoreCase(Language.get("YES"))) {
                    accepted = true;
                } else if (resp.equalsIgnoreCase(Language.get("NO"))) {
                    output.accept(Language.get("BOARD_SIZE_FORMAT"));
                    boardDimensions = InputParser.parseBoardSize(input.get());
                    output.accept(Language.get("UNDERSTOOD") + " " + Arrays.toString(boardDimensions));
                    parameters = new BoardParameters(boardDimensions[0], boardDimensions[1], boardDimensions[2]);
                    board = new Board(parameters);
                    output.accept(board.toString() + "\n");
                    if (!parameters.isChanged())
                        accepted = true;
                }
            }
        } else
            output.accept(board.toString() + "\n");
        return new PlayerSetUpState(this);
    }

}
