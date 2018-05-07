package edu.tseidler.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PlayerList {
    private final List<Player> playersList;


    public PlayerList() {
        Player first = new Player("jacek", BoardField.X, true);
        this.playersList = new LinkedList<Player>() {{
            add(first);
            add(Player.second(first, "placek"));
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
        StringBuilder sb = new StringBuilder(Language.get("PLAYERS"))
                .append("\n" + Language.get("PLAYER") + " 1:\n")
                .append(getNext().toString())
                .append("\n" + Language.get("PLAYER") + " 2:\n")
                .append(getNext().toString());
        return sb.toString();
    }
}
