package edu.tseidler.model;

import java.util.HashMap;
import java.util.Map;

public class Fields {
    private final Map<Coordinates, BoardField> fields;
    private Coordinates lastCoords = new Coordinates(-1, -1);
    private BoardField lastMark = BoardField.EMPTY;

    public Fields() {
        fields = new HashMap<>();
    }

    public BoardField get(Coordinates coordinate) {
        return fields.getOrDefault(coordinate, BoardField.EMPTY);
    }

    public boolean put(Coordinates coords, BoardField sign) {
        if (get(coords) == BoardField.EMPTY) {
            fields.put(coords, sign);
            lastCoords = coords;
            lastMark = sign;
            return true;
        }
        return false;
    }

    public int getTakenFieldsNumber() {
        return fields.size();
    }

    public Coordinates getLastCoords() {
        return lastCoords;
    }

    public BoardField getLastMark() {
        return lastMark;
    }

    public void clear() {
        fields.clear();
    }
}
