package edu.tseidler.service;

import edu.tseidler.model.Board;
import edu.tseidler.model.BoardField;
import edu.tseidler.model.Coordinates;
import edu.tseidler.model.Fields;

import java.util.Collections;

public class Drawer {
    private final int maxRow;
    private final int widthRow;
    private final int maxCol;
    private final int widthCol;
    private final StringBuilder sb = new StringBuilder();

    public Drawer(Board board) {
        maxRow = board.maxRow;
        widthRow = Math.max(
                Integer.toString(board.maxRow).length(),
                3);
        maxCol = board.maxCol;
        widthCol = Math.max(
                Integer.toString(board.maxCol).length(),
                3);
    }

    public String draw(Fields fields) {
        drawColumnNumbering();
        drawHorizontalLine();
        for (int i = 0; i < maxRow; i++) {
            sb.append(adjustWidthToCol((i+1) + "|"));
            for (int j = 0; j < maxCol; j++) {
                BoardField current = fields.get(new Coordinates(i + 1, j + 1));
                String sign = current == BoardField.EMPTY ? " " : current.toString();
                sign += "|";
                sb.append(adjustWidthToCol(sign));
            }
            sb.append("\n");
            drawHorizontalLine();
        }
        return sb.toString();
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
        return String.format(" %" + widthCol + "s", content);
    }

    private void drawHorizontalLine() {
        sb.append(repeatSequence(" ", (widthRow+1)));
        for (int i = 0; i < maxCol; i++) {
            sb.append(repeatSequence("-", (widthCol+1)));
        }
        sb.append("\n");
    }

}
