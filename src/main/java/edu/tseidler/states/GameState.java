package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class GameState {
    Consumer<String> output;
    Supplier<String> input;
    Language lang;
    Board board;
    PlayerList players;

    static int gamesPlayed = 0;

    GameState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        this.output = output;
        this.input = input;
        this.lang = lang;
        this.board = board;
        this.players = players;
    }

    GameState(GameState previousState) {
        this.output = previousState.output;
        this.input = previousState.input;
        this.lang = previousState.lang;
        this.board = previousState.board;
        this.players = previousState.players;
    }

    abstract GameState getNextState();
}
