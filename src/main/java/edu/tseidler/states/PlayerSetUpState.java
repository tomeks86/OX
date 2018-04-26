package edu.tseidler.states;

import edu.tseidler.model.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayerSetUpState extends GameState {
    PlayerSetUpState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        String player1_name = getPlayerName(1);

        BoardField default_mark = BoardField.X;
        BoardField player1_mark = getPlayerMark(default_mark);

        boolean default_start = true;
        boolean start = getPlayer1Start(default_start);

        String player2_name = getPlayerName(2);

        Player player1 = new Player(player1_name, player1_mark, start);
        Player player2 = Player.second(player1, player2_name);
        players.add(player1);
        players.add(player2);
        return new SetupSummaryState(output, input, lang, board, players);
    }

    private String getPlayerName(int n) {
        String pl_default = players.getNext().name;
        output.accept(lang.get("PLAYER") + n + " " + lang.get("DEFAULT") + ": " + pl_default);
        String player_name = input.get();
        return player_name.isEmpty() ? pl_default : player_name;
    }

    private BoardField getPlayerMark(BoardField default_mark) {
        output.accept(lang.get("PLAYER") + "1 " + lang.get("SIGN") + " " + lang.get("DEFAULT") + ": " + default_mark);
        BoardField player1_mark;
        try {
            player1_mark = BoardField.valueOf(input.get());
        } catch (IllegalArgumentException e) {
            player1_mark = default_mark;
        }
        return player1_mark;
    }

    private boolean getPlayer1Start(boolean default_start) {
        boolean start = default_start;
        output.accept(lang.get("START?") + " " + lang.get("YESORNO"));
        if (input.get().equalsIgnoreCase(lang.get("NO"))) {
            start = false;
        }
        return start;
    }
}
