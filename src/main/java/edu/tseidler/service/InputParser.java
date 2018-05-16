package edu.tseidler.service;

import edu.tseidler.Main;
import edu.tseidler.model.Language;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static int[] parseBoardSize(String input) {
        input = input.trim();

        int[] dimensionsAndWinningSequenceLength = new int[3];
        String pattern = "(?:\\[?)\\s*(?<maxRows>-?\\d+)\\s*,?\\s*(?<maxCols>-?\\d+)\\s*(?:]?),?\\s*(?<winning>-?\\d+)?";
        Matcher m = Pattern.compile(pattern).matcher(input);
        try {
            if (m.matches()) {
                dimensionsAndWinningSequenceLength[0] = Integer.valueOf(m.group("maxRows"));
                dimensionsAndWinningSequenceLength[1] = Integer.valueOf(m.group("maxCols"));
                dimensionsAndWinningSequenceLength[2] = Integer.valueOf(Optional.ofNullable(m.group("winning")).orElse("3"));
            }
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, e.getMessage());
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
