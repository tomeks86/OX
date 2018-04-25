package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class BoardSetUpState implements GameState {
    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("BOARD_SETUP"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        int[] boardDimensions = new int[2];
        try {
            boardDimensions = (new InputParser()).parseBoardSize(inputSupplier.get());
        } catch (IllegalStateException e) {
            boardDimensions = new int[] {3, 3};
        }
        return new GameOverState();
    }

}
