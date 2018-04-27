package edu.tseidler.service;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.Coordinates;
import edu.tseidler.model.Fields;

import java.util.Collections;

public class Drawer {
    public static final int MINIUMUM_FIELD_WIDTH = 3;
    private final int maxRow;
    private final int widthRow;
    private final int maxCol;
    private final int widthCol;
    private final StringBuilder sb = new StringBuilder();
    private final Fields fields;

    public Drawer(Board board) {
        maxRow = board.maxRow;
        widthRow = getFieldWidth(maxRow);
        maxCol = board.maxCol;
        widthCol = getFieldWidth(maxCol);
        fields = board.getFields();
    }

    private int getFieldWidth(int maxValue) {
        return Math.max(
                Integer.toString(maxValue).length(),
                MINIUMUM_FIELD_WIDTH);
    }

    public String draw() {
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
        String sign = current == BoardField.EMPTY ? " " : current.toString();
        sign += "|";
        sb.append(adjustWidthToCol(sign));
    }

    private String repeatSequence(String toBeRepeated, int times) {
        return String.join("", Collections.nCopies(times, toBeRepeated));
    }

    private void drawColumnNumbering() {
        sb.append(repeatSequence(" ", widthRow + 1));
        for (int i = 0; i < maxCol; i++) {
            sb.append(adjustWidthToCol(Integer.toString(i+1)));
        }
        sb.append("\n");
    }

    private String adjustWidthToCol(String content) {
        String myFormat = " %" + widthCol + "s";
        return String.format(myFormat, content);
    }

    private void drawHorizontalLine() {
        sb.append(repeatSequence(" ", (widthRow+1)));
        for (int i = 0; i < maxCol; i++) {
            sb.append(repeatSequence("-", (widthCol+1)));
        }
        sb.append("\n");
    }

}
