package edu.tseidler.service;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.BoardParameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WinnerValidatorTest {
    private Board board;

    @BeforeMethod
    public void boardSetUp() {
        board = new Board(new BoardParameters(4, 5, 3));

        IntStream.of(1, 3, 5, 13).forEach(i -> board.put(i, BoardField.O));
    }

    @DataProvider
    private static Iterator<Object[]> missedPositions() {
        return new Iterator<Object[]>() {
            private Iterator<Integer> myIter = Arrays.asList(6, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20).iterator();

            @Override
            public boolean hasNext() {
                return myIter.hasNext();
            }

            @Override
            public Object[] next() {
                return new Object[] {myIter.next()};
            }
        };
    }

    @Test(dataProvider = "missedPositions")
    public void shouldBeNoWinner(int position) {
        board.put(position, BoardField.O);

        assertFalse(WinnerValidator.test(board));
    }

    @Test
    public void shouldFindWinnerOnHorizontal() {
        board.put(2, BoardField.O);

        assertTrue(WinnerValidator.test(board));
    }

    @Test
    public void shouldFindWinnerOnVertical() {
        board.put(8, BoardField.O);

        assertTrue(WinnerValidator.test(board));
    }

    @Test
    private void shouldFindWinnerOnDiagonal() {
        board.put(7, BoardField.O);

        assertTrue(WinnerValidator.test(board));
    }

    @Test
    private void shouldFindWinnerOnAntiDiagonal() {
        board.put(9, BoardField.O);

        assertTrue(WinnerValidator.test(board));
    }
}
