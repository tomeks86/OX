package edu.tseidler.states;

import edu.tseidler.model.Language;
import edu.tseidler.model.Player;

public class WinnerState extends GameState {
    private final Player winnerPlayer;

    public WinnerState(GameState gameState, Player currentPlayer) {
        super(gameState);
        winnerPlayer = currentPlayer;
        currentPlayer.win();
    }

    @Override
    GameState getNextState() {
        output.accept(board.draw());
        output.accept(gameWinnerStatement(winnerPlayer) + "\n");
        board.clear();
        GameState.gamesPlayed++;
        return new Running(this);
    }

    private String gameWinnerStatement(Player winnerPlayer) {
        return Language.build("_PLAYER_ " + winnerPlayer.getName() + " _WON_ _GAME_");
    }
}
