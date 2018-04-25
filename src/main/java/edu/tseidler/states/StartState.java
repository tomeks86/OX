package edu.tseidler.states;

import edu.tseidler.Board;
import edu.tseidler.LanguageSelector;
import edu.tseidler.PlayerList;
import edu.tseidler.states.GameState;
import edu.tseidler.states.LanguageSetupState;
import edu.tseidler.states.SetupSummaryState;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StartState implements GameState {
    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("WELCOME"));
        output.accept(lang.get("SETUP"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        if (inputSupplier.get().equalsIgnoreCase(lang.get("YES")))
            return new LanguageSetupState(lang);
        else
            return new SetupSummaryState(LanguageSelector.selectLanguage("en"), new Board(new int[] {3, 3}, 3), new PlayerList()); // todo running state
    }

}
