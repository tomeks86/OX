package edu.tseidler.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class BoardTest {

    public static final int[] DEFAULT_DIMENSIONS = {3, 3, 3};
    private Board board;

    @DataProvider
    private static final Object[][] goodInitializationParameters() {
        return new Object[][] {
                {new int[] {10, 3, 3}},
                {DEFAULT_DIMENSIONS},
                {new int[] {3, 10, 3}},
                {new int[] {30, 10, 4}},
        };
    }

    @Test(dataProvider = "goodInitializationParameters")
    public void shouldInitializeBoard(int[] dimensions) {
        board = new Board(dimensions);

        assertThat(board.get(new Coordinates(1,1))).isEqualTo(BoardField.EMPTY);
    }

    @DataProvider
    private static final Object[][] poorInitializationParameters() {
        return new Object[][] {
                {new int[] {10, 1, 6}, new int[] {10, 3, 3}},
                {new int[] {3, 3, 1}, DEFAULT_DIMENSIONS},
                {new int[] {3, 1, 3}, DEFAULT_DIMENSIONS},
                {new int[] {3, 1, 4}, DEFAULT_DIMENSIONS},
        };
    }

    @Test(dataProvider = "poorInitializationParameters")
    public void shouldInitializeWithCorrectedParameters(int[] dimensions, int[] expected) {
        board = new Board(dimensions);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(board.maxRow, expected[0], "wrong maxRows");
        sa.assertEquals(board.maxCol, expected[1], "wrong maxCols");
        sa.assertEquals(board.winningNumber, expected[2], "wrong winningNumber");
        sa.assertAll();
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
        board = new Board(DEFAULT_DIMENSIONS);

        boolean put = board.put(new Coordinates(row, col), BoardField.X);

        assertThat(put).isTrue();
    }

    @Test(dataProvider = "coordinatesForBoardPut")
    public void shouldNotPutXOrOOnBoardAtAlreadyTakenField(int row, int col) {
        board = new Board(DEFAULT_DIMENSIONS);
        board.put(new Coordinates(1, 2), BoardField.O);
        board.put(new Coordinates(1, 3), BoardField.O);
        board.put(new Coordinates(1, 1), BoardField.O);
        board.put(new Coordinates(2, 2), BoardField.O);

        boolean put = board.put(new Coordinates(row, col), BoardField.X);

        assertThat(put).isFalse();
    }

    @DataProvider
    private static Object[][] coordinatesToShootOutOfBoard() {
        return new Object[][] {
                {4, 3},
                {4, -3},
                {-4, -3},
                {1, 5},
                {3, 4},
        };
    }

    @Test(dataProvider = "coordinatesToShootOutOfBoard")
    public void shouldReturnFalseForOutOfBoundsShootOnTheBoard(int row, int col) {
        board = new Board(DEFAULT_DIMENSIONS);

        boolean put = board.put(new Coordinates(row, col), BoardField.O);

        assertThat(put).isFalse();
    }

    @Test
    public void shouldFindXOnBoard() {
        board = new Board(DEFAULT_DIMENSIONS);
        Coordinates coords = new Coordinates(2, 3);

        board.put(coords, BoardField.X);

        assertThat(board.get(coords)).isEqualTo(BoardField.X);
    }

    @DataProvider
    private static Object[][] parametersForBoardInitializationWithNumberOfWinningFields() {
        return new Object[][] {
                {new int[] {5, 3, 3}},
                {DEFAULT_DIMENSIONS},
                {new int[] {3, 6, 3}},
                {new int[] {30, 10, 3}},
        };
    }

    @Test(dataProvider = "parametersForBoardInitializationWithNumberOfWinningFields")
    public void setBoardWinningNumber(int[] dimensionsAndWinningNumber) {
        board = new Board(dimensionsAndWinningNumber);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(board.maxRow, dimensionsAndWinningNumber[0]);
        sa.assertEquals(board.maxCol, dimensionsAndWinningNumber[1]);
        sa.assertEquals(board.winningNumber, dimensionsAndWinningNumber[2]);
        sa.assertAll();
    }
}
