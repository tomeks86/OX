package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class OutPutSetupState extends GameState {
    public OutPutSetupState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    GameState getNextState() {
        output.accept(lang.get("CHOOSE_OUTPUT"));
        String choice = input.get();
        // todo: enable output to file
        return new BoardSetUpState(output, input, lang, board, players);
    }
}
