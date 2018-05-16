package edu.tseidler.model;

import edu.tseidler.service.Drawer;

public class Board {
    private final BoardParameters boardParameters;
    private final Fields fields;
    final Drawer drawer;

    public Board(BoardParameters boardParameters) {
        this.boardParameters = boardParameters;
        fields = new Fields();
        drawer = new Drawer(this);
    }

    public int getMaxRow() {
        return boardParameters.getMaxRow();
    }

    public int getMaxCol() {
        return boardParameters.getMaxCol();
    }

    int getWinningNumber() {
        return boardParameters.getWinningNumber();
    }

    public BoardField get(Coordinates coordinates) {
        return fields.get(coordinates);
    }

    boolean put(Coordinates coords, BoardField sign) {
        if (areCoordsValid(coords))
            return fields.put(coords, sign);
        return false;
    }

    public int put(int coord, BoardField sign) {
        coord -= 1;
        if (coord >= 0 && coord < getMaxRow() * getMaxCol()) {
            int row = coord / getMaxCol();
            int col = coord % getMaxCol();
            if (put(new Coordinates(row, col), sign))
                return coord + 1;
            else
                return -1;
        }
        return -1;
    }

    private boolean areCoordsValid(Coordinates coords) {
        return coords.getRow() >= 0 && coords.getRow() < getMaxRow() && coords.getCol() >= 0 && coords.getCol() < getMaxCol();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Language.get("BOARD"))
                .append(":\n")
                .append(Language.get("MAX_ROWS"))
                .append(": " + getMaxRow() + "\n")
                .append(Language.get("MAX_COLS"))
                .append(": " + getMaxCol() + "\n")
                .append(Language.get("WINNING_NUMBER"))
                .append(": " + getWinningNumber());
        return sb.toString();
    }

    public String draw() {
        return drawer.draw();
    }

    public Fields getFields() {
        return fields;
    }

    public boolean ifFull() {
        return getMaxRow() * getMaxCol() == fields.getTakenFieldsNumber();
    }

    public boolean doWeHaveAWinner() {
        Coordinates lastCoords = fields.getLastCoords();
        BoardField lastMark = fields.getLastMark();

        return (checkGeneralizedWinner(lastCoords, lastMark, 1, 0)) ||
                checkGeneralizedWinner(lastCoords, lastMark, 0, 1) ||
                checkGeneralizedWinner(lastCoords, lastMark, 1, -1) ||
                checkGeneralizedWinner(lastCoords, lastMark, 1, 1);
    }


    private boolean checkGeneralizedWinner(Coordinates lastCoords, BoardField lastMark, int rowInc, int colInc) {
        int currentWinning = 1;

        int curRow = lastCoords.getRow();
        int curCol = lastCoords.getCol();
        while (true) {
            curRow += rowInc;
            curCol += colInc;
            Coordinates coords = new Coordinates(curRow, curCol);
            if (!areCoordsValid(coords))
                break;
            if (fields.get(coords) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == getWinningNumber())
                return true;
        }
        curRow = lastCoords.getRow();
        curCol = lastCoords.getCol();
        while (true) {
            curRow -= rowInc;
            curCol -= colInc;
            Coordinates coords = new Coordinates(curRow, curCol);
            if (!areCoordsValid(coords))
                break;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == getWinningNumber())
                return true;
        }
        return false;
    }

    public void clear() {
        fields.clear();
    }

}
