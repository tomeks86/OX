package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupState extends GameState {
    SetupState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("WELCOME"));
        output.accept(Language.get("SETUP"));
        if (input.get().equalsIgnoreCase(Language.get("YES")))
            return new LanguageSetupState(this);
        else
            return new PlayerSetUpState(this);
    }

}
