package edu.tseidler.states;

import edu.tseidler.Main;
import edu.tseidler.model.Board;
import edu.tseidler.model.BoardParameters;
import edu.tseidler.model.Language;
import edu.tseidler.service.InputParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class BoardSetUpState extends GameState {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static final int DEFAULT_DIMENSION = 3;

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
                output.accept(Language.build("_ACCEPT_BOARD_ _YESORNO_"));
                String resp = input.get();
                while (isResponseOtherThanYesOrNo(resp)) {
                    output.accept(Language.build("_ACCEPT_BOARD_ _YESORNO_"));
                    resp = input.get();
                }
                if (resp.equalsIgnoreCase(Language.get("YES"))) {
                    accepted = true;
                } else if (resp.equalsIgnoreCase(Language.get("NO"))) {
                    return new BoardSetUpState(this);
                }
            }
        }
        return new PlayerSetUpState(this);
    }

    private BoardParameters getBoardParameters() {
        BoardParameters parameters;
        promptBoardSetup();
        String[] rawBoardParameters = InputParser.parseBoardSize(input.get());
        output.accept(Language.get("UNDERSTOOD") + " " + Arrays.toString(rawBoardParameters));
        int[] boardDimensions = digestRawBoardParameters(rawBoardParameters);
        parameters = new BoardParameters(boardDimensions[0], boardDimensions[1], boardDimensions[2]);
        board = new Board(parameters);
        output.accept(board.toString() + "\n");
        return parameters;
    }

    private int[] digestRawBoardParameters(String[] rawBoardParameters) {
        int[] boardDimensions = new int[3];
        for (int i = 0; i < 3; i++) {
            try {
                boardDimensions[i] = Integer.valueOf(rawBoardParameters[i]);
            } catch (NumberFormatException e) {
                logger.log(Level.WARN, e.getMessage());
                logger.log(Level.WARN, "using default of " + DEFAULT_DIMENSION);
                boardDimensions[i] = DEFAULT_DIMENSION;
            }
        }
        return boardDimensions;
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
