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
        BoardParameters parameters;
        parameters = getBoardParameters();
        if (parameters.areChanged()) {
            output.accept(Language.get("BOARD_PARAMETERS_CHANGED"));
            boolean accepted = false;
            while (!accepted) {
                output.accept(Language.build("_ACCEPT_ _YESORNO_"));
                String resp = input.get();
                while (isResponseOtherThanYesOrNo(resp)) {
                    output.accept(Language.build("_ACCEPT_ _YESORNO_"));
                    resp = input.get();
                }
                if (resp.equalsIgnoreCase(Language.get("YES"))) {
                    accepted = true;
                } else if (resp.equalsIgnoreCase(Language.get("NO"))) {
                    parameters = getBoardParameters();
                    if (!parameters.areChanged())
                        accepted = true;
                }
            }
        }
        return new PlayerSetUpState(this);
    }

    private BoardParameters getBoardParameters() {
        int[] boardDimensions;
        BoardParameters parameters;
        promptBoardSetup();
        boardDimensions = InputParser.parseBoardSize(input.get());
        output.accept(Language.get("UNDERSTOOD") + " " + Arrays.toString(boardDimensions));
        parameters = new BoardParameters(boardDimensions[0], boardDimensions[1], boardDimensions[2]);
        board = new Board(parameters);
        output.accept(board.toString() + "\n");
        return parameters;
    }

    private void promptBoardSetup() {
        output.accept(Language.get("BOARD_SIZE_FORMAT1"));
        output.accept(Language.get("BOARD_SIZE_FORMAT2"));
        output.accept(Language.get("BOARD_SIZE_DEFAULT"));
    }

    private boolean isResponseOtherThanYesOrNo(String resp) {
        return !(Language.get("YES").equalsIgnoreCase(resp) || Language.get("NO").equalsIgnoreCase(resp));
    }

}
