package edu.tseidler.states;

import edu.tseidler.Board;
import edu.tseidler.PlayerList;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupSummaryState implements GameState {
    private final Map<String, String> lang;
    private final Board board;
    private final PlayerList playerList;

    public SetupSummaryState(Map<String,String> lang, Board board, PlayerList playerList) {
        this.lang = lang;
        this.board = board;
        this.playerList = playerList;
    }

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.getOrDefault("SUMMARY", "no message provided"));
        output.accept("en");
        output.accept(board.toString());
        output.accept(playerList.toString());
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        return new GameOverState();
    }
}
