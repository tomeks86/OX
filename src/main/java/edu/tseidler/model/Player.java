package edu.tseidler.model;

public class Player {
    public final String name;
    public final BoardField mark;
    public final boolean first;

    public Player(String name, BoardField mark, boolean first) {
        this.name = name;
        this.mark = mark;
        this.first = first;
    }

    public static Player second(Player player, String name) {
        return new Player(name, player.mark.other(), !player.first);
    }

    public String present(Language lang) {
        StringBuilder sb = new StringBuilder(lang.get("PLAYER_NAME"))
                .append(": " + name +"\n")
                .append(lang.get("PLAYER_MARK"))
                .append(": " + mark);
        if (first)
            sb.append(" (" + lang.get("FIRST") + ")");
        return sb.toString();
    }
}
