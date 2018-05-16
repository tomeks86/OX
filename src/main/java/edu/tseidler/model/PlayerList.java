package edu.tseidler.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PlayerList {
    public static final String PLAYER1_DEFAULT = "jacek";
    public static final String PLAYER2_DEFAULT = "placek";
    private final List<Player> playersList;


    public PlayerList() {
        Player first = new Player(PLAYER1_DEFAULT, BoardField.X, true);
        this.playersList = new LinkedList<Player>() {{
            add(first);
            add(Player.second(first, PLAYER2_DEFAULT));
        }};
    }

    public void add(Player player) {
        int index = player.isFirst() ? 0 : 1;
        playersList.set(index, player);
    }

    public Player getNext() {
        Player first = playersList.remove(0);
        playersList.add(first);
        return first;
    }

    public void sort() {
        playersList.sort(Comparator.comparingInt(Player::getScore).reversed());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n" + Language.get("PLAYERS") + ":\n")
                .append(Language.build("_FIRST_ _PLAYER_ :"))
                .append("\n")
                .append(getNext().toString())
                .append("\n")
                .append(Language.build("_SECOND_ _PLAYER_ :"))
                .append("\n")
                .append(getNext().toString());
        return sb.toString();
    }

    public void switchStartingPlayer() {
        Player player1 = getNext();
        Player player2 = getNext();

        if (player1.isFirst()) {
            switchStartingPlayer(player1, player2);
            inversePlayerListOrdering(player1, player2);
        } else {
            switchStartingPlayer(player2, player1);
        }
    }

    private void inversePlayerListOrdering(Player player1, Player player2) {
        playersList.clear();
        playersList.add(player2);
        playersList.add(player1);
    }

    private void switchStartingPlayer(Player player1, Player player2) {
        player1.unsetFirst();
        player2.setFirst();
    }
}
