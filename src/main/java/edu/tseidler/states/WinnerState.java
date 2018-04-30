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
        output.accept(gameWinnerStatement(winnerPlayer));
        output.accept(board.draw());
        board.clear();
        GameState.gamesPlayed++;
        return new Running(this);
    }

    private String gameWinnerStatement(Player winnerPlayer) {
        return Language.build("PLAYER " + winnerPlayer.getName() + " WON GAME");
    }
}
