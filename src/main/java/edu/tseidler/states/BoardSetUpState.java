package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;
import edu.tseidler.service.InputParser;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardSetUpState extends GameState {
    BoardSetUpState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        output.accept(this.lang.get("BOARD_SETUP"));
        output.accept(this.lang.get("BOARD_SIZE_FORMAT"));
        int[] boardDimensions = InputParser.parseBoardSize(input.get());
        try {
            boardDimensions = (new InputParser()).parseBoardSize(input.get());
        } catch (IllegalStateException e) {
            boardDimensions = new int[]{3, 3, 3};
        }
        return new PlayerSetUpState(output, input, lang, new Board(boardDimensions), players);
    }

}
