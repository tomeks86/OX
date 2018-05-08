package edu.tseidler.model;

public class BoardParameters {
    private final int maxRow;
    private final int maxCol;
    private final int winningNumber;
    private static final int MINIMUM_SIZE_AND_WINNING_NUMBER = 3;
    private boolean changed = false;

    public BoardParameters(int maxRow, int maxCol, int winningNumber) {
        int maxRowValidated = Math.max(MINIMUM_SIZE_AND_WINNING_NUMBER, maxRow);
        int maxColValidated = Math.max(MINIMUM_SIZE_AND_WINNING_NUMBER, maxCol);
        int winningNumberValidated = Math.max(
                MINIMUM_SIZE_AND_WINNING_NUMBER,
                getPossibleWinningNumber(maxRowValidated, maxColValidated, winningNumber));
        this.maxRow = maxRowValidated;
        this.maxCol = maxColValidated;
        this.winningNumber = winningNumberValidated;
        if (maxRowValidated != maxRow || maxColValidated != maxCol || winningNumberValidated != winningNumber)
            changed = true;
    }

    private int getPossibleWinningNumber(int maxRow, int maxCol, int proposedWinningNumber) {
        return Math.min(
                Math.min(maxRow, maxCol),
                proposedWinningNumber);
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

    public boolean isChanged() {
        return changed;
    }
}
