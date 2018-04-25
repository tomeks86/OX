package edu.tseidler.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    public int[] parseBoardSize(String input) {
        input = input.trim();
        int[] result = new int[] {3, 3};
        String pattern = "\\[\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\]";
        Matcher m = Pattern.compile(pattern).matcher(input);
        if (m.matches()) {
            int dim1 = Integer.valueOf(m.group(1));
            int dim2 = Integer.valueOf(m.group(2));
            if ((dim1 >= 1 && dim2 >=3) || (dim1 >=3 && dim2 >=1)) {
                result[0] = dim1;
                result[1] = dim2;
            }
        }
        return result;
    }
}
