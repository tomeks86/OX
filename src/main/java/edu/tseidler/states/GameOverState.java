package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOverState extends GameState {
    GameOverState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(board.draw());
        output.accept(lang.get("GAME_OVER"));
        GameState.gamesPlayed = 3;
        return this;
    }
}
