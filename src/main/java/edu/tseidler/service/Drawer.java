package edu.tseidler.service;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.Coordinates;
import edu.tseidler.model.Fields;

import java.util.Collections;

public class Drawer {
    public static final int MINIUMUM_FIELD_WIDTH = 3;
    private final int maxRow;
    private final int maxCol;
    private final int maxWidth;
    private final StringBuilder sb = new StringBuilder();
    private final Fields fields;

    public Drawer(Board board) {
        maxRow = board.maxRow;
        maxCol = board.maxCol;
        maxWidth = getFieldWidth(maxRow * maxCol);
        fields = board.getFields();
    }

    private int getFieldWidth(int maxValue) {
        return Math.max(
                Integer.toString(maxValue).length() + 1,
                MINIUMUM_FIELD_WIDTH);
    }

    public String draw() {
        sb.setLength(0);    // fixme: this class has to be rearranged to get rid of this strange reset...
        drawColumnNumbering();
        drawHorizontalLine();
        drawBoardBody();
        return sb.toString();
    }

    private void drawBoardBody() {
        for (int i = 0; i < maxRow; i++) {
            sb.append(adjustWidthToCol((i+1) + "|"));
            for (int j = 0; j < maxCol; j++) {
                drawSign(i, j);
            }
            sb.append("\n");
            drawHorizontalLine();
        }
    }

    private void drawSign(int i, int j) {
        BoardField current = fields.get(new Coordinates(i, j));
        String sign = current == BoardField.EMPTY ? adjustWidthToCol(String.valueOf(i * maxCol + j + 1) + "|") : adjustWidthToColColored(current.toString() + "|");
        sb.append(sign);
    }

    private String adjustWidthToColColored(String content) {
        String myFormat = " %" + (maxWidth+9) + "s";    // 9 is some kind of magic number... connected with ANSI String coloring
        return String.format(myFormat, content);
    }

    private String repeatSequence(String toBeRepeated, int times) {
        return String.join("", Collections.nCopies(times, toBeRepeated));
    }

    private void drawColumnNumbering() {
        sb.append(repeatSequence(" ", maxWidth + 1));
        for (int i = 0; i < maxCol; i++) {
            sb.append(adjustWidthToCol(Integer.toString(i+1)));
        }
        sb.append("\n");
    }

    private String adjustWidthToCol(String content) {
        String myFormat = " %" + maxWidth + "s";
        return String.format(myFormat, content);
    }

    private void drawHorizontalLine() {
        sb.append(repeatSequence(" ", (maxWidth+1)));
        for (int i = 0; i < maxCol; i++) {
            sb.append(repeatSequence("-", (maxWidth+1)));
        }
        sb.append("\n");
    }

}
