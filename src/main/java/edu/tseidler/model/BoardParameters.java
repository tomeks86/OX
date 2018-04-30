package edu.tseidler.model;

class BoardParameters {
    private final int maxRow;
    private final int maxCol;
    private final int winningNumber;

    BoardParameters(int maxRow, int maxCol, int winningNumber) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.winningNumber = winningNumber;
    }

    int getMaxRow() {
        return maxRow;
    }

    int getMaxCol() {
        return maxCol;
    }

    int getWinningNumber() {
        return winningNumber;
    }
}
