package edu.tseidler.model;

public class Coordinates {
    public final int row;
    public final int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return row == that.row &&
                col == that.col;
    }

    @Override
    public int hashCode() {
        return row * col + col;
    }
}
