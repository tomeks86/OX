package edu.tseidler.model;

public class BoardParameters {
    private final int maxRow;
    private final int maxCol;
    private final int winningNumber;
    private static final int MINIMUM_SIZE_AND_WINNING_NUMBER = 3;
    private static final int MAXIMUM_SIZE_AND_WINNING_NUMBER = 200;
    private boolean changed;

    public BoardParameters(int maxRow, int maxCol, int winningNumber) {
        int maxRowValidated = Math.max(MINIMUM_SIZE_AND_WINNING_NUMBER, maxRow);
        maxRowValidated = Math.min(MAXIMUM_SIZE_AND_WINNING_NUMBER, maxRowValidated);
        int maxColValidated = Math.max(MINIMUM_SIZE_AND_WINNING_NUMBER, maxCol);
        maxColValidated = Math.min(MAXIMUM_SIZE_AND_WINNING_NUMBER, maxColValidated);
        int winningNumberValidated = Math.max(
                MINIMUM_SIZE_AND_WINNING_NUMBER,
                getPossibleWinningNumber(maxRowValidated, maxColValidated, winningNumber));
        this.maxRow = maxRowValidated;
        this.maxCol = maxColValidated;
        this.winningNumber = winningNumberValidated;
        changed = maxRowValidated != maxRow || maxColValidated != maxCol || winningNumberValidated != winningNumber;
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

    public boolean areChanged() {
        return changed;
    }
}
