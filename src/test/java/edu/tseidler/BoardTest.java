package edu.tseidler;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.Coordinates;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class BoardTest {

    private Board board;

    @DataProvider
    private static final Object[][] goodInitializationParameters() {
        return new Object[][] {
                {new int[] {1, 3}},
                {new int[] {3, 3}},
                {new int[] {3, 1}},
                {new int[] {30, 10}},
        };
    }

    @Test(dataProvider = "goodInitializationParameters")
    public void shouldInitializeBoard(int[] dimensions) {
        board = new Board(dimensions);

        assertThat(board.get(new Coordinates(1,1))).isEqualTo(BoardField.EMPTY);
    }

    @DataProvider
    private static Object[][] coordinatesForBoardPut() {
        return new Object[][] {
                {1, 2},
                {1, 3},
                {1, 1},
                {2, 2},
        };
    }

    @Test(dataProvider = "coordinatesForBoardPut")
    public void shouldPutXOrOOnBoard(int row, int col) {
        board = new Board(new int[] {3, 3});

        boolean put = board.put(new Coordinates(row, col), BoardField.X);

        assertThat(put).isTrue();
    }

    @Test
    public void shouldFindXOnBoard() {
        board = new Board(new int[] {3, 3});
        Coordinates coords = new Coordinates(2, 3);

        board.put(coords, BoardField.X);

        assertThat(board.get(coords)).isEqualTo(BoardField.X);
    }

    @DataProvider
    private static Object[][] parametersForBoardInitializationWithNumberOfWinningFields() {
        return new Object[][] {
                {new int[] {5, 3}, 2},
                {new int[] {3, 3}, 3},
                {new int[] {3, 6}, 2},
                {new int[] {30, 10}, 5},
        };
    }

    @Test(dataProvider = "parametersForBoardInitializationWithNumberOfWinningFields")
    public void setBoardWinningNumber(int[] dimensionsAndWinningNumber) {
        board = new Board(dimensionsAndWinningNumber);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(board.getMaxRows(), dimensionsAndWinningNumber[0]);
        sa.assertEquals(board.getMaxCols(), dimensionsAndWinningNumber[1]);
        sa.assertEquals(board.getWinningNumber(), dimensionsAndWinningNumber[2]);
        sa.assertAll();
    }
}
