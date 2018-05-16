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
        String player1Name = getPlayerName("1");
        output.accept(Language.build("_SELECTED_NAME_ " + player1Name));

        BoardField default_mark = BoardField.X;
        BoardField player1Mark = getPlayerMark(default_mark);
        output.accept(Language.build("_SELECTED_MARK_ " + player1Mark));

        boolean defaultStart = true;
        boolean start = getPlayer1Start(defaultStart);
        output.accept(Language.get("SELECTED") + " " + (start ? Language.get("FIRST") : Language.get("SECOND")));

        String player2Name = getPlayerName("2");
        output.accept(Language.build("_SELECTED_NAME_ " + player2Name));

        Player player1 = new Player(player1Name, player1Mark, start);
        Player player2 = Player.second(player1, player2Name);
        players.add(player1);
        players.add(player2);
        return new SetupSummaryState(this);
    }

    private String getPlayerName(String number) {
        String pl_default = players.getNext().getName();
        output.accept(Language.build("_PLAYER_ " + number + " _DEFAULT_ : " + pl_default + " _EMPTY_NOT_ALLOWED_"));
        String playerName = sanitize(input.get());
        return playerName.isEmpty() ? pl_default : playerName;
    }

    private String sanitize(String input) {
        return input.replaceAll("_", "").trim();
    }

    private BoardField getPlayerMark(BoardField default_mark) {
        output.accept(Language.build("_PLAYER_ 1 _SIGN_ ( _DEFAULT_ ) : " + default_mark));
        BoardField player1_mark = default_mark;
        String choice = input.get().toUpperCase();
        if (choice.isEmpty()) return player1_mark;
        boolean provided = false;
        while (!provided) {
            try {
                player1_mark = BoardField.valueOf(choice);
                if (player1_mark == BoardField.EMPTY) throw new IllegalArgumentException();
                provided = true;
            } catch (IllegalArgumentException e) {
                output.accept(Language.build("_CHOSE_ X / O"));
                choice = input.get().toUpperCase();
            }
        }
        return player1_mark;
    }

    private boolean getPlayer1Start(boolean defaultStart) {
        boolean start = defaultStart;
        output.accept(Language.build("_START?_ _YESORNO_"));
        output.accept(Language.build("_DEFAULT_ _YES_"));
        String choice = input.get();
        while (isNotYesOrNo(choice)) {
            if (choice.isEmpty())
                return defaultStart;
            output.accept(Language.build("_AGAIN_ : _YYESORNO_"));
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
