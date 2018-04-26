package edu.tseidler.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {
    @DataProvider
    private static final Object[][] dimensionsOnlyToParse() {
        return new Object[][]{
                {"[ 3, 3]", new int[]{3, 3, 3}},
                {"[2,4  ]", new int[]{3, 4, 3}},
                {"[  10,1]", new int[]{10, 3, 3}},
                {"  [100,50]", new int[]{100, 50, 3}},
                {"[100 ,   500 ] ", new int[]{100, 500, 3}},
        };
    }

    @Test(dataProvider = "dimensionsOnlyToParse")
    public void shouldParseBoardSize(String input, int[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(InputParser.parseBoardSize(input));
    }

    @DataProvider
    private static final Object[][] dataForIncorrectBoardSize() {
        return new Object[][] {
                {"[ 1, 1]"},
                {"[ 1, 1, 1]"},
                {"[ 1, 2]"},
                {"[ 2, 2]"},
                {"[-2,-4  ]"},
                {"[  10,-1]"},
                {"  [-100,50]"},
                {"[-1 ,   1 ] "},
                {"ala ma kota"}
        };
    }

    @Test(dataProvider = "dataForIncorrectBoardSize")
    public void shouldReturnDefaultBoardSize(String input) {
        assertThat(new int[] {3, 3, 3}).isEqualTo(InputParser.parseBoardSize(input));
    }

    @DataProvider
    private static final Object[][] dimensionsAndWinningNumberToParse() {
        return new Object[][]{
                {"[ 3, 3] 5", new int[]{3, 3, 3}},
                {"[2,4  ] 10", new int[]{3, 4, 3}},
                {"[  10,1]   15 ", new int[]{10, 3, 3}},
                {"  [100,50] 4", new int[]{100, 50, 4}},
                {"[100 ,   500 ] 8", new int[]{100, 500, 8}},
                {"[100 ,   500 ] -5", new int[]{3, 3, 3}},
        };
    }

    @Test(dataProvider = "dimensionsAndWinningNumberToParse")
    public void shouldParseBoardSizeWithWinningNumber(String input, int[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(InputParser.parseBoardSize(input));
    }
}
