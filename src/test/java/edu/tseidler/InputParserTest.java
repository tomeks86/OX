package edu.tseidler;

import edu.tseidler.service.InputParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {
    private InputParser inputParser = new InputParser();

    @DataProvider
    private static final Object[][] dataForSuccessfulBoardSizeParsing() {
        return new Object[][]{
                {"[ 3, 3]", new int[]{3, 3}},
                {"[2,4  ]", new int[]{2, 4}},
                {"[  10,1]", new int[]{10, 1}},
                {"  [100,50]", new int[]{100, 50}},
                {"[100 ,   500 ] ", new int[]{100, 500}},
        };
    }

    @Test(dataProvider = "dataForSuccessfulBoardSizeParsing")
    public void shouldParseBoardSize(String input, int[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(inputParser.parseBoardSize(input));
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
        assertThat(new int[] {3, 3}).isEqualTo(inputParser.parseBoardSize(input));
    }
}
