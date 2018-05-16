package edu.tseidler.states;

import edu.tseidler.model.Language;
import edu.tseidler.model.Player;

public class GameOverState extends GameState {
    GameOverState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
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
            return Language.build("_MATCH_ _ENDED_ _DRAWEM_") +"" +
                    "\n" + Language.build(" _FINAL_ _SCORES_") + " : \n" +
                    Language.build(first.toString() + " _SCORE_") + " " + first.getScore() + "\n" +
                    Language.build(second.toString() + " _SCORE_") + " " + second.getScore();
        } else
            return Language.build(first.toString() + " _WINNER_ _SCORE_") + " " + first.getScore() + "\n" +
                    Language.build(second.toString() + " _LOOSER_ _SCORE_") + " " + second.getScore();
    }
}
