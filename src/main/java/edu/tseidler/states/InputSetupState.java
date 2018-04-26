package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputSetupState extends GameState {
    public InputSetupState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    GameState getNextState() {
        output.accept(lang.get("CHOOSE_INPUT"));
        String choice = input.get();
        // todo: enable receiving from file
        return new OutPutSetupState(output, input, lang, board, players);
    }
}
