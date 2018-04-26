package edu.tseidler.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int maxRow;
    private final int maxCol;
    final int winningNumber;
    private Map<Coordinates, BoardField> fields;

    public Board(int[] dimensionsAndWinningNumber) {
        maxRow = Math.max(3, dimensionsAndWinningNumber[0]);
        maxCol = Math.max(3, dimensionsAndWinningNumber[1]);
        this.winningNumber = Math.max(
                3,
                Math.min(
                        Math.min(maxRow, maxCol),
                        dimensionsAndWinningNumber[2]));
        fields = new HashMap<>(maxRow * maxCol);
    }

    public BoardField get(Coordinates coordinate) {
        if (coordinate.row <= maxRow && coordinate.col <= maxCol)
            return fields.getOrDefault(coordinate, BoardField.EMPTY);
        return null;
    }

    public boolean put(Coordinates coords, BoardField sign) {
        if (get(coords) == BoardField.EMPTY) {
            fields.put(coords, sign);
            return true;
        }
        return false;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    public int getMaxRows() {
        return maxRow;
    }

    public int getMaxCols() {
        return maxCol;
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
        StringBuilder sb = new StringBuilder();
        drawHorizontalLine(sb);
        for (int i = 0; i < maxRow; i++) {
            sb.append("|");
            for (int j = 0; j < maxCol; j++) {
                drawSign(i, j, sb);
            }
            sb.append("\n");
            drawHorizontalLine(sb);
        }
        return sb.toString();
    }

    private void drawSign(int i, int j, StringBuilder sb) {
        BoardField current = get(new Coordinates(i+1, j+1));
        String sign = current == BoardField.EMPTY ? " " : current.toString();
        sb.append(sign + "|");
    }

    private void drawHorizontalLine(StringBuilder sb) {
        sb.append("-");
        for (int i = 0; i < maxRow; i++) {
            sb.append("--");
        }
        sb.append("\n");
    }
}
