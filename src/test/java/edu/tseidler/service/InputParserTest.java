package edu.tseidler.service;

import edu.tseidler.model.Language;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {

    private static final String[] DEFAULT_DIMENSIONS = {"3", "3", "3"};
    private static final int DEFAULT_FAILSAFE_PLAYER_MARK_CHOICE = -1;
    private Language lang = new Language("en");

    @DataProvider
    private static final Object[][] dimensionsOnlyToParse() {
        return new Object[][]{
                {"[ 3, 3]", DEFAULT_DIMENSIONS},
                {"[2,4  ]", new String[]{"2", "4", "3"}},
                {"[  10,1]", new String[]{"10", "1", "3"}},
                {"  [100,50]", new String[]{"100", "50", "3"}},
                {"[100 ,   500 ] ", new String[]{"100", "500", "3"}},
                {"[ 1, 1]", new String[] {"1", "1", "3"}},
                {"[ 1, 1, 1]", new String[] {"0", "0", "0"}},
                {"[ 1, 2]", new String[] {"1", "2", "3"}},
                {"[ 2, 2]", new String[] {"2", "2", "3"}},
                {"[-2,-4  ]", new String[] {"-2", "-4", "3"}},
                {"[  10,-1]", new String[] {"10", "-1", "3"}},
                {"  [-100,50]", new String[] {"-100", "50", "3"}},
                {"[-1 ,   1 ] ", new String[] {"-1", "1", "3"}},
                {"ala ma kota", new String[] {"0", "0", "0"}},
                {"3, 4 5", new String[] {"3", "4", "5"}},
                {"3, 4, 5", new String[] {"3", "4", "5"}},
                {"3  4  5", new String[] {"3", "4", "5"}},
        };
    }

    @Test(dataProvider = "dimensionsOnlyToParse")
    public void shouldParseBoardSize(String input, String[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(InputParser.parseBoardSize(input));
    }

    @DataProvider
    private static final Object[][] dimensionsAndWinningNumberToParse() {
        return new Object[][]{
                {"[ 3, 3] 5", new String[]{"3", "3", "5"}},
                {"[2,4  ] 10", new String[]{"2", "4", "10"}},
                {"[  10,1]   15 ", new String[]{"10", "1", "15"}},
                {"  [100,50] 4", new String[]{"100", "50", "4"}},
                {"[100 ,   500 ] 8", new String[]{"100", "500", "8"}},
                {"[100 ,   500 ] -5", new String[] {"100", "500", "-5"}},
        };
    }

    @Test(dataProvider = "dimensionsAndWinningNumberToParse")
    public void shouldParseBoardSizeWithWinningNumber(String input, String[] expectedDimensions) {
        assertThat(expectedDimensions).isEqualTo(InputParser.parseBoardSize(input));
    }

    @DataProvider
    private static final Object[][] exampleCorrectPlayerInputForMark() {
        return new Object[][] {
                {"  1 ", 1},
                {"   10", 10},
                {"59  ", 59},
                {"06", 6},
        };
    }

    @Test(dataProvider = "exampleCorrectPlayerInputForMark")
    public void shouldSuccessfullyParsePlayerMarkInput(String input, int coordinate) {
        assertThat(InputParser.parsePlayerMarkInput(input)).isEqualTo(coordinate);
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

    @Test(expectedExceptions = GameQuitException.class)
    public void shouldThrowGameQuitExceptionWhenQuit() {
        InputParser.parsePlayerMarkInput("quit");
    }
}
