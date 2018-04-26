package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayerSetUpState implements GameState {
    private final Language lang;
    private final Board board;
    private PlayerList playerList;

    PlayerSetUpState(Language lang, Board board) {
        this.lang = lang;
        this.board = board;
    }

    @Override
    public void printTo(Consumer<String> output, Language lang) {
//        output.accept(lang.get);
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Language lang) {
        return new SetupSummaryState(this.lang, this.board, new PlayerList());
    }

}
