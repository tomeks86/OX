package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StartState implements GameState {
    @Override
    public void printTo(Consumer<String> output, Language lang) {
        output.accept(lang.get("WELCOME"));
        output.accept(lang.get("SETUP"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Language lang) {
        if (inputSupplier.get().equalsIgnoreCase(lang.get("YES")))
            return new LanguageSetupState(lang);
        else
            return new SetupSummaryState(new Language("en"), new Board(new int[]{3, 3, 3}), new PlayerList()); // todo running state
    }

}
