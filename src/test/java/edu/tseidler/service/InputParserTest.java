package edu.tseidler.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class InputParserTest {

    public static final int[] DEFAULT_DIMENSIONS = {3, 3, 3};
    public static final int[] DEFAULT_FAILSAFE_PLAYER_MARK_CHOICE = {-1, -1};

    @DataProvider
    private static final Object[][] dimensionsOnlyToParse() {
        return new Object[][]{
                {"[ 3, 3]", DEFAULT_DIMENSIONS},
                {"[2,4  ]", new int[]{2, 4, 3}},
                {"[  10,1]", new int[]{10, 1, 3}},
                {"  [100,50]", new int[]{100, 50, 3}},
                {"[100 ,   500 ] ", new int[]{100, 500, 3}},
                {"[ 1, 1]", new int[] {1, 1, 3}},
                {"[ 1, 1, 1]", DEFAULT_DIMENSIONS},
                {"[ 1, 2]", new int[] {1, 2, 3}},
                {"[ 2, 2]", new int[] {2, 2, 3}},
                {"[-2,-4  ]", DEFAULT_DIMENSIONS},
                {"[  10,-1]", DEFAULT_DIMENSIONS},
                {"  [-100,50]", DEFAULT_DIMENSIONS},
                {"[-1 ,   1 ] ", DEFAULT_DIMENSIONS},
                {"ala ma kota", DEFAULT_DIMENSIONS}
        };
    }

    @Test(dataProvider = "dimensionsOnlyToParse")
    public void shouldParseBoardSize(String input, int[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(InputParser.parseBoardSize(input));
    }

    @DataProvider
    private static final Object[][] dimensionsAndWinningNumberToParse() {
        return new Object[][]{
                {"[ 3, 3] 5", new int[]{3, 3, 5}},
                {"[2,4  ] 10", new int[]{2, 4, 10}},
                {"[  10,1]   15 ", new int[]{10, 1, 15}},
                {"  [100,50] 4", new int[]{100, 50, 4}},
                {"[100 ,   500 ] 8", new int[]{100, 500, 8}},
                {"[100 ,   500 ] -5", DEFAULT_DIMENSIONS},
        };
    }

    @Test(dataProvider = "dimensionsAndWinningNumberToParse")
    public void shouldParseBoardSizeWithWinningNumber(String input, int[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(InputParser.parseBoardSize(input));
    }

    @DataProvider
    private static final Object[][] exampleCorrectPlayerInputForMark() {
        return new Object[][] {
                {"1 3", new int[] {1, 3}},
                {"    10 13", new int[] {10, 13}},
                {"5   9  ", new int[] {5, 9}},
                {"0 6", new int[] {0, 6}},
        };
    }

    @Test(dataProvider = "exampleCorrectPlayerInputForMark")
    public void shouldSuccessfullyParsePlayerMarkInput(String input, int[] coords) {
        assertThat(InputParser.parsePlayerMarkInput(input)).isEqualTo(coords);
    }

    @DataProvider
    private static final Object[][] exampleIncorrectPlayerInputForMark() {
        return new Object[][] {
                {"1 3 5"},
                {"    d10 13"},
                {"5   -9  "},
                {"0 6S"},
        };
    }

    @Test(dataProvider = "exampleIncorrectPlayerInputForMark")
    public void shouldReturnDefaultFailsafeMarkWhenIncorrectInputFromPlayer(String input) {
        assertThat(InputParser.parsePlayerMarkInput(input)).isEqualTo(DEFAULT_FAILSAFE_PLAYER_MARK_CHOICE);
    }
}
