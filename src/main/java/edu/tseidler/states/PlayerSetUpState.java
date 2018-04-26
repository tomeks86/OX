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
        output.accept(lang.get("PLAYER1_NAME"));
        String player1_name = input.get();
        output.accept(lang.get("PLAYER1_SIGN"));
        BoardField player1_mark;
        try {
            player1_mark = BoardField.valueOf(input.get());
        } catch (IllegalArgumentException e) {
            player1_mark = BoardField.X;
        }
        output.accept(lang.get("START?"));
        boolean start = true;
        if (input.get().equalsIgnoreCase(lang.get("NO"))) {
            start = false;
        }
        Player player1 = new Player(player1_name, player1_mark, start);
        output.accept("PLAYER2_NAME");
        String player2_name = input.get();
        Player player2 = Player.second(player1, player2_name);
        players.add(player1);
        players.add(player2);
        return new SetupSummaryState(output, input, lang, board, players);
    }

}
