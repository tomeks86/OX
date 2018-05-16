package edu.tseidler.states;

import edu.tseidler.model.Language;
import edu.tseidler.model.Player;

class WinnerState extends GameState {
    private final Player winnerPlayer;
    private final Player looserPlayer;

    WinnerState(GameState gameState) {
        super(gameState);
        winnerPlayer = gameState.players.getNext();
        looserPlayer = gameState.players.getNext();
        winnerPlayer.win();
    }

    @Override
    GameState getNextState() {
        output.accept(board.draw());
        output.accept(gameWinnerStatement(winnerPlayer) + "\n");
        board.clear();
        players.switchStarting();
        GameState.gamesPlayed++;
        return new Running(this);
    }

    private String gameWinnerStatement(Player winnerPlayer) {
        return Language.get("WINNING") + " " + winnerPlayer.getMark() + ". " + winnerPlayer.getMark() + ": " + winnerPlayer.getScore() +
        " " + looserPlayer.getMark() + ": " + looserPlayer.getScore();
    }
}
