package edu.tseidler;

import java.util.function.Consumer;

public class SetUp implements GameState {
    @Override
    public void printTo(Consumer<String> output) {
        output.accept("abc");
    }

    @Override
    public GameState getNextState() {
        return this;
    }
}
