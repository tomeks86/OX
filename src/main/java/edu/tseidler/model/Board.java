package edu.tseidler.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int maxRow;
    private final int maxCol;
    final int winningNumber;
    private Map<Coordinates, BoardField> fields;

    public Board(int[] dimensionsAndWinningNumber) {
        maxRow = dimensionsAndWinningNumber[0];
        maxCol = dimensionsAndWinningNumber[1];
        this.winningNumber = dimensionsAndWinningNumber[2];
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Board{");
        sb.append("maxRow=").append(maxRow);
        sb.append(", maxCol=").append(maxCol);
        sb.append(", winningNumber=").append(winningNumber);
        sb.append('}');
        return sb.toString();
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
}