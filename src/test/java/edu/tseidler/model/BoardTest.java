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
        sa.assertEquals(board.getMaxRow(), expected[0], "wrong maxRows");
        sa.assertEquals(board.getMaxCol(), expected[1], "wrong maxCols");
        sa.assertEquals(board.getWinningNumber(), expected[2], "wrong winningNumber");
        sa.assertAll();
    }

    @DataProvider
    private static Object[][] coordinatesForBoardPut() {
        return new Object[][] {
                {0, 1},
                {0, 2},
                {0, 1},
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
        board.put(new Coordinates(0, 1), BoardField.O);
        board.put(new Coordinates(0, 2), BoardField.O);
        board.put(new Coordinates(0, 0), BoardField.O);
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
        Coordinates coords = new Coordinates(1, 2);

        board.put(coords, BoardField.X);

        assertThat(board.get(coords)).isEqualTo(BoardField.X);
    }

    @Test
    public void shouldFillAllTheBoardWithX() {
        board = new Board(DEFAULT_DIMENSIONS);
        for (int i = 0; i < 10; i++) {
            board.put(i, BoardField.X);
        }

        assertThat(board.ifFull()).isTrue();
    }

    @DataProvider
    private static Object[][] simplifiedAndFullCoordinatesForBoardMarkingTest() {
        return new Object[][] {
                {10, new int[] {1, 4}},
                {13, new int[] {2, 2}},
                {3, new int[] {0, 2}},
                {20, new int[] {3, 4}},
        };
    }

    @Test(dataProvider = "simplifiedAndFullCoordinatesForBoardMarkingTest")
    public void shouldFindMarkAfterPuttingWithSingleCoordinate(int simpleCoord, int[] complexCoord) {
        board = new Board(new int[]{4, 5, 3});

        board.put(simpleCoord, BoardField.O);

        assertThat(board.get(new Coordinates(complexCoord[0], complexCoord[1]))).isEqualTo(BoardField.O);
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
        sa.assertEquals(board.getMaxRow(), dimensionsAndWinningNumber[0]);
        sa.assertEquals(board.getMaxCol(), dimensionsAndWinningNumber[1]);
        sa.assertEquals(board.getWinningNumber(), dimensionsAndWinningNumber[2]);
        sa.assertAll();
    }
}
