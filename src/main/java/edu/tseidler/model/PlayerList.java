package edu.tseidler.model;

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
        int index = player.first ? 0 : 1;
        playersList.set(index, player);
    }

    public Player getNext() {
        Player first = playersList.remove(0);
        playersList.add(first);
        return first;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PlayerList{");
        sb.append("playersList=").append(playersList);
        sb.append('}');
        return sb.toString();
    }

    public String present(Language lang) {
        StringBuilder sb = new StringBuilder(lang.get("PLAYERS"))
                .append("\n" + lang.get("PLAYER") + " 1:\n")
                .append(getNext().present(lang))
                .append("\n" + lang.get("PLAYER") + " 2:\n")
                .append(getNext().present(lang));
        return sb.toString();
    }
}
