package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardParameters;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {
    private Supplier<String> inputSupplier;
    private Consumer<String> output;
    private GameState currentState;
    private Language lang;

    public OXGame(Supplier<String> inputSupplier, Consumer<String> output, String selLang) {
        this.inputSupplier = inputSupplier;
        this.output = output;
        this.lang = new Language(selLang);
        this.currentState = new StartState(output, inputSupplier, new Language("en"), new Board(new BoardParameters(3, 3, 3)), new PlayerList());
    }

    public void start() {
        while (GameState.gamesPlayed < 4) {
            doOneCycle();
        }
    }

    private void doOneCycle() {
        this.currentState = currentState.getNextState();
    }
}
