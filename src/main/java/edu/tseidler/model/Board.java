package edu.tseidler.model;

import edu.tseidler.service.Drawer;

public class Board {
    private static final int MINIMUM_SIZE_AND_WINNING_NUMBER = 3;
    private final int maxRow;
    private final int maxCol;
    private final int winningNumber;
    private final Fields fields;
    final Drawer drawer;

    public Board(int[] dimensionsAndWinningNumber) {
        maxRow = Math.max(MINIMUM_SIZE_AND_WINNING_NUMBER, dimensionsAndWinningNumber[0]);
        maxCol = Math.max(MINIMUM_SIZE_AND_WINNING_NUMBER, dimensionsAndWinningNumber[1]);
        this.winningNumber = Math.max(
                MINIMUM_SIZE_AND_WINNING_NUMBER,
                getPossibleWinningNumber(dimensionsAndWinningNumber[2]));
        fields = new Fields();
        drawer = new Drawer(this);
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    private int getPossibleWinningNumber(int b) {
        return Math.min(
                Math.min(maxRow, maxCol),
                b);
    }

    public BoardField get(Coordinates coordinate) {
        if (coordinate.getRow() <= maxRow && coordinate.getCol() <= maxCol)
            return fields.get(coordinate);
        return null;
    }

    public boolean put(Coordinates coords, BoardField sign) {
        if (areCoordsValid(coords))
            return fields.put(coords, sign);
        return false;
    }

    public boolean put(int coord, BoardField sign) {
        coord -= 1;
        if (coord >= 0 && coord < maxRow * maxCol) {
            int row = coord / maxCol;
            int col = coord % maxCol;
            return put(new Coordinates(row, col), sign);
        }
        return false;
    }

    private boolean areCoordsValid(Coordinates coords) {
        return coords.getRow() >= 0 && coords.getRow() < maxRow && coords.getCol() >= 0 && coords.getCol() < maxCol;
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
        return drawer.draw();
    }

    public Fields getFields() {
        return fields;
    }

    public boolean ifFull() {
        return maxCol * maxCol == fields.getTakenFieldsNumber();
    }

    public boolean doWeHaveAWinner() {
        Coordinates lastCoords = fields.getLastCoords();
        BoardField lastMark = fields.getLastMark();

        return (checkHorizontalWinner(lastCoords, lastMark) ||
                checkVerticalWinner(lastCoords, lastMark) ||
                checkDiagonalWinner(lastCoords, lastMark) ||
                checkAndiDiagonalWinner(lastCoords, lastMark));
    }

    private boolean checkHorizontalWinner(Coordinates lastCoords, BoardField lastMark) {
        int currentWinning = 1;

        // horizontal
        // left
        int curRow = lastCoords.getRow();
        int curCol = lastCoords.getCol();
        while (curCol > 0) {
            curCol--;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        // reset column position
        curCol = lastCoords.getCol();
        while (curCol < maxCol - 1) {
            curCol++;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        return false;
    }

    private boolean checkVerticalWinner(Coordinates lastCoords, BoardField lastMark) {
        int currentWinning = 1;
        // vertical
        // top
        currentWinning = 1;
        int curRow = lastCoords.getRow();
        int curCol = lastCoords.getCol();
        while (curRow < maxRow - 1) {
            curRow++;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        // bottom
        curRow = lastCoords.getRow();
        while (curRow > 0) {
            curRow--;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        return false;
    }

    private boolean checkDiagonalWinner(Coordinates lastCoords, BoardField lastMark) {
        int currentWinning = 1;
        // diagonal
        currentWinning = 1;
        int curCol = lastCoords.getCol();
        int curRow = lastCoords.getRow();
        // right up
        while (curRow > 0 && curCol < maxCol - 1) {
            curRow--;
            curCol++;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        // left down
        curCol = lastCoords.getCol();
        curRow = lastCoords.getRow();
        while (curRow < maxRow - 1 && curCol > 0) {
            curRow++;
            curCol--;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        return false;
    }

    private boolean checkAndiDiagonalWinner(Coordinates lastCoords, BoardField lastMark) {
        int currentWinning = 1;
        // anti-diagonal
        // left up
        currentWinning = 1;
        int curRow = lastCoords.getRow();
        int curCol = lastCoords.getCol();
        while (curRow > 0 && curCol > 0) {
            curRow--;
            curCol--;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        // right down
        curRow = lastCoords.getRow();
        curCol = lastCoords.getCol();
        while (curRow < maxRow - 1 && curCol < maxCol - 1) {
            curRow++;
            curCol++;
            if (fields.get(new Coordinates(curRow, curCol)) == lastMark)
                currentWinning++;
            else
                break;
            if (currentWinning == winningNumber)
                return true;
        }
        return false;
    }

}
