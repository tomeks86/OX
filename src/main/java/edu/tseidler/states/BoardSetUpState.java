package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.service.InputParser;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardSetUpState implements GameState {
    private final Map<String, String> lang;

    BoardSetUpState(Map<String, String> lang) {
        this.lang = lang;
    }

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(this.lang.get("BOARD_SETUP"));
        output.accept(this.lang.get("BOARD_SIZE_FORMAT"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        int[] boardDimensions;
        try {
            boardDimensions = (new InputParser()).parseBoardSize(inputSupplier.get());
        } catch (IllegalStateException e) {
            boardDimensions = new int[] {3, 3};
        }
        return new PlayerSetUpState(this.lang, new Board(boardDimensions));
    }

}
