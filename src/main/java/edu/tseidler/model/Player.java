package edu.tseidler.model;

public class Player {
    private final String name;
    private final BoardField mark;
    private final boolean first;
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
    public String toString() {
        StringBuilder sb = new StringBuilder(Language.build("_PLAYER_ _NAME_"))
                .append(": " + name +"\n")
                .append(Language.build("_PLAYER_ _MARK_"))
                .append(": " + mark);
        if (first)
            sb.append(" (" + Language.get("FIRST") + ")");
        return sb.toString();
    }
}
