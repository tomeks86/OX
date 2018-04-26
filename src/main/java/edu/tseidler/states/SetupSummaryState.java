package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupSummaryState implements GameState {
    private final Language lang;
    private final Board board;
    private final PlayerList playerList;

    public SetupSummaryState(Language lang, Board board, PlayerList playerList) {
        this.lang = lang;
        this.board = board;
        this.playerList = playerList;
    }

    @Override
    public void printTo(Consumer<String> output, Language lang) {
        output.accept(lang.get("SUMMARY"));
        output.accept(lang.toString());
        output.accept(board.toString());
        output.accept(playerList.toString());
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Language lang) {
        return new GameOverState();
    }
}
