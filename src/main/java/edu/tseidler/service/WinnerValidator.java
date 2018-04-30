package edu.tseidler.service;

import edu.tseidler.model.Board;

public class WinnerValidator {
    public static boolean test(Board board) {
        return board.doWeHaveAWinner();
    }
}
