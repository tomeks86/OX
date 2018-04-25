package edu.tseidler;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int maxRow;
    private final int maxCol;
    final int winningNumber;
    private Map<Coordinates, BoardField> fields;

    public Board(int[] dimensions, int winningNumber) {
        maxRow = dimensions[0];
        maxCol = dimensions[1];
        this.winningNumber = winningNumber;
        fields = new HashMap<>(maxRow * maxCol);
    }

    public Board(int[] dimensions) {
        maxRow = dimensions[0];
        maxCol = dimensions[1];
        this.winningNumber = 3;
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
}
