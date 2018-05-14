package edu.tseidler.states;

import edu.tseidler.model.BoardField;
import edu.tseidler.model.Language;
import edu.tseidler.model.Player;

public class PlayerSetUpState extends GameState {
    PlayerSetUpState(GameState previousState) {
        super(previousState);
    }

    @Override
    public GameState getNextState() {
        String player1_name = getPlayerName("1");

        BoardField default_mark = BoardField.X;
        BoardField player1_mark = getPlayerMark(default_mark);

        boolean default_start = true;
        boolean start = getPlayer1Start(default_start);

        String player2_name = getPlayerName("2");

        Player player1 = new Player(player1_name, player1_mark, start);
        Player player2 = Player.second(player1, player2_name);
        players.add(player1);
        players.add(player2);
        return new SetupSummaryState(this);
    }

    private String getPlayerName(String n) {
        String pl_default = players.getNext().getName();
        output.accept(Language.build("_PLAYER_ " + n + " _DEFAULT_ : " + pl_default));
        String player_name = input.get();
        return player_name.isEmpty() ? pl_default : player_name;
    }

    private BoardField getPlayerMark(BoardField default_mark) {
        output.accept(Language.build("_PLAYER_ 1 _SIGN_ ( _DEFAULT_ ) : " + default_mark));
        BoardField player1_mark = default_mark;
        String choice = input.get().toUpperCase();
        if (choice.isEmpty()) return player1_mark;
        boolean provided = false;
        while (!provided) {
            try {
                BoardField.valueOf(choice);
                provided = true;
            } catch (IllegalArgumentException e) {
                output.accept("X / O");
                choice = input.get().toUpperCase();
            }
        }
        return player1_mark;
    }

    private boolean getPlayer1Start(boolean default_start) {
        boolean start = default_start;
        output.accept(Language.build("_START?_ _YESORNO_"));
        output.accept(Language.build("_DEFAULT_ _YES_"));
        String choice = input.get();
        while (isNotYesOrNo(choice)) {
            if (choice.isEmpty())
                return default_start;
            output.accept(Language.build("_AGAIN_ _YESORNO_"));
            choice = input.get();
        }
        if (choice.equalsIgnoreCase(lang.get("NO"))) {
            start = false;
        }
        return start;
    }

    private boolean isNotYesOrNo(String resp) {
        return !(Language.get("YES").equalsIgnoreCase(resp) || Language.get("NO").equalsIgnoreCase(resp));
    }
}
