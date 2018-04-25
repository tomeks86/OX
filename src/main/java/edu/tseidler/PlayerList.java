package edu.tseidler;

import java.util.LinkedList;
import java.util.List;

public class PlayerList {
    private final List<Player> playersList;


    public PlayerList() {
        Player first = Player.first("jacek", BoardField.X);
        this.playersList = new LinkedList<Player>() {{
            add(Player.first("jacek", BoardField.X));
            add(Player.second(first, "placek"));
        }};
    }

    public void add(Player player) {
        int index = player.first ? 0 : 1;
        playersList.add(index, player);
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
}
