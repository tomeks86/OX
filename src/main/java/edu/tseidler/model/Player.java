package edu.tseidler.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                mark == player.mark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mark);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", mark=").append(mark);
        sb.append('}');
        return sb.toString();
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
