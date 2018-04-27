package edu.tseidler.model;

import edu.tseidler.service.Drawer;

public class Board {
    public final int maxRow;
    public final int maxCol;
    public final int winningNumber;
    private Fields fields;
    final Drawer drawer;

    public Board(int[] dimensionsAndWinningNumber) {
        maxRow = Math.max(3, dimensionsAndWinningNumber[0]);
        maxCol = Math.max(3, dimensionsAndWinningNumber[1]);
        this.winningNumber = Math.max(
                3,
                Math.min(
                        Math.min(maxRow, maxCol),
                        dimensionsAndWinningNumber[2]));
        fields = new Fields();
        drawer = new Drawer(this);
    }

    public BoardField get(Coordinates coordinate) {
        if (coordinate.row <= maxRow && coordinate.col <= maxCol)
            return fields.get(coordinate);
        return null;
    }

    public boolean put(Coordinates coords, BoardField sign) {
        if (areCoordsValid(coords))
            return fields.put(coords, sign);
        return false;
    }

    private boolean areCoordsValid(Coordinates coords) {
        return coords.row > 0 && coords.row <= maxRow && coords.col > 0 && coords.col <= maxCol;
    }

    public String present(Language lang) {
        StringBuilder sb = new StringBuilder(lang.get("BOARD"))
                .append(":\n")
                .append(lang.get("MAX_ROWS"))
                .append(": " + maxRow + "\n")
                .append(lang.get("MAX_COLS"))
                .append(": " + maxCol + "\n")
                .append(lang.get("WINNING_NUMBER"))
                .append(": " + winningNumber);
        return sb.toString();
    }

    public String draw() {
        return drawer.draw(fields);
    }
}
