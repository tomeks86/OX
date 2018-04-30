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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Language.build("PLAYER NAME"))
                .append(": " + name +"\n")
                .append(Language.build("PLAYER MARK"))
                .append(": " + mark);
        if (first)
            sb.append(" (" + Language.get("FIRST") + ")");
        return sb.toString();
    }
}
