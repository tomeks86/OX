package edu.tseidler.states;

import edu.tseidler.model.Language;
import edu.tseidler.model.Player;

class WinnerState extends GameState {
    private final Player winnerPlayer;
    private final Player looserPlayer;

    WinnerState(GameState gameState) {
        super(gameState);
        looserPlayer = gameState.players.getNext();
        winnerPlayer = gameState.players.getNext();
        winnerPlayer.win();
    }

    @Override
    GameState getNextState() {
        output.accept(board.draw());
        output.accept(gameWinnerStatement(winnerPlayer) + "\n");
        board.clear();
        players.switchStartingPlayer();
        GameState.gamesPlayed++;
        return new Running(this);
    }

    private String gameWinnerStatement(Player winnerPlayer) {
        return Language.get("WINNING") + " " + winnerPlayer.getMark() + ". " + winnerPlayer.getMark() + ": " + winnerPlayer.getScore() +
        " " + looserPlayer.getMark() + ": " + looserPlayer.getScore();
    }
}
