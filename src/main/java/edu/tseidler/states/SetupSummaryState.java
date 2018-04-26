package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupSummaryState extends GameState {
    SetupSummaryState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("SUMMARY"));
        output.accept(lang.toString());
        output.accept(board.toString());
        output.accept(players.toString());
        return new GameOverState(output, input, lang, board, players);
    }
}
