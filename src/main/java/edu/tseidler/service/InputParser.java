package edu.tseidler.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    public static int[] parseBoardSize(String input) {
        input = input.trim();
        int[] dimensionsAndWinningNumber = new int[] {3, 3, 3};
        String pattern = "(?:\\[?)\\s*(?<maxRows>\\d+)\\s*,\\s*(?<maxCols>\\d+)\\s*(?:]?)\\s*(?<winning>\\d+)?";
        Matcher m = Pattern.compile(pattern).matcher(input);
        if (m.matches()) {
            dimensionsAndWinningNumber[0] = Integer.valueOf(m.group("maxRows"));
            dimensionsAndWinningNumber[1] = Integer.valueOf(m.group("maxCols"));
            dimensionsAndWinningNumber[2] = Integer.valueOf(Optional.ofNullable(m.group("winning")).orElse("3"));
        }
        return dimensionsAndWinningNumber;
    }


    public static int parsePlayerMarkInput(String input) {
        int result = -1;
        try {
            result = Integer.valueOf(input.trim());
        } catch (NumberFormatException e) {
            // OK to continue program gets default value of -1
        }
        return result;
    }
}
