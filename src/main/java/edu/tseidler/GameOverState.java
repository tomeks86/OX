package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class GameOverState implements GameState {
    static int gamesPlayed = 0;

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("GAME_OVER"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        gamesPlayed = 3;
        return this;
    }
}
