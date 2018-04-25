package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class PlayerSetUpState implements GameState {
    private final Map<String, String> lang;
    private final Board board;

    PlayerSetUpState(Map<String, String> lang, Board board) {
        this.lang = lang;
        this.board = board;
    }

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
//        output.accept(lang.get);
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        return new GameOverState();
    }
}
