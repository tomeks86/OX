package edu.tseidler;

import java.util.function.Consumer;

public interface GameState {
    void printTo(Consumer<String> output);

    GameState getNextState();
}
