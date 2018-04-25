package edu.tseidler;

import java.util.Objects;

public class Player {
    final String name;
    final BoardField mark;
    final boolean first;

    private Player(String name, BoardField mark, boolean first) {
        this.name = name;
        this.mark = mark;
        this.first = first;
    }

    public static Player first(String name, BoardField mark) {
        return new Player(name, mark, true);
    }

    public static Player second(Player player, String name) {
        return new Player(name, player.mark.other(), false);
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
}
