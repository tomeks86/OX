package edu.tseidler.states;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOverState implements GameState {

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("GAME_OVER"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        OXGame.gamesPlayed = 3;
        return this;
    }
}
