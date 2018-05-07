package edu.tseidler.model;

import java.util.Objects;

public class Player {
    private final String name;
    private final BoardField mark;
    private boolean first;
    private int score;

    public Player(String name, BoardField mark, boolean first) {
        this.name = name;
        this.mark = mark;
        this.first = first;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public BoardField getMark() {
        return mark;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst() {
        this.first = true;
    }

    public void unsetFirst() {
        this.first = false;
    }

    public int getScore() {
        return score;
    }

    public void win() {
        score += 3;
    }

    public void draw() {
        score += 1;
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
        StringBuilder sb = new StringBuilder(Language.build("_PLAYER_ _NAME_"))
                .append(": " + name +"\n")
                .append(Language.build("_PLAYER_ _MARK_"))
                .append(": " + mark);
        return sb.toString();
    }
}
