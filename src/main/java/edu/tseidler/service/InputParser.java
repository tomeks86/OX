package edu.tseidler.service;

import edu.tseidler.model.Language;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    public static String[] parseBoardSize(String input) {
        input = input.trim();
        String[] dimensionsAndWinningSequenceLength = new String[]{"0", "0", "0"};
        String pattern = "(?:\\[?)\\s*(?<maxRows>-?\\d+)\\s*,?\\s*(?<maxCols>-?\\d+)\\s*(?:]?),?\\s*(?<winning>-?\\d+)?";
        Matcher m = Pattern.compile(pattern).matcher(input);
        if (m.matches()) {
            dimensionsAndWinningSequenceLength[0] = m.group("maxRows");
            dimensionsAndWinningSequenceLength[1] = m.group("maxCols");
            dimensionsAndWinningSequenceLength[2] = Optional.ofNullable(m.group("winning")).orElse("3");
        }
        return dimensionsAndWinningSequenceLength;
    }

    public static int parsePlayerMarkInput(String input) {
        int result = -1;
        try {
            result = Integer.valueOf(input.trim());
        } catch (NumberFormatException e) {
            if (input.equalsIgnoreCase(Language.get("QUIT")))
                throw new GameQuitException();
            // otherwise OK to continue program gets default value of -1
        }
        return result;
    }
}
