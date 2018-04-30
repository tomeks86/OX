package edu.tseidler.states;

import edu.tseidler.model.Language;
import edu.tseidler.model.Player;

public class GameOverState extends GameState {
    GameOverState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        output.accept(board.draw());
        output.accept(Language.get("GAME_OVER"));
        output.accept(matchStatistics());
        GameState.gamesPlayed++;
        return this;
    }

    private String matchStatistics() {
        players.sort();
        Player first = players.getNext();
        Player second = players.getNext();
        if (first.getScore() == second.getScore()) {
            return Language.build("MATCH DRAW");
        } else
            return Language.build(first.toString() + " WINNER SCORE") + " " + first.getScore() + "\n" +
                    Language.build(second.toString() + " LOOSER SCORE") + " " + second.getScore();
    }
}
