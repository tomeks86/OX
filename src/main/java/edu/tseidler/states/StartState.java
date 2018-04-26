package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class StartState extends GameState {
    StartState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("WELCOME"));
        output.accept(lang.get("SETUP"));
        if (input.get().equalsIgnoreCase(lang.get("YES")))
            return new LanguageSetupState(output, input, lang, board, players);
        else
            return new SetupSummaryState(output, input, lang, board, players); // todo running state
    }

}
