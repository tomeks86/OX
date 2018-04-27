package edu.tseidler.model;

import java.util.HashMap;
import java.util.Map;

public class Fields {
    private final Map<Coordinates, BoardField> fields;

    public Fields() {
        fields = new HashMap<>();
    }

    public BoardField get(Coordinates coordinate) {
        return fields.getOrDefault(coordinate, BoardField.EMPTY);
    }

    public boolean put(Coordinates coords, BoardField sign) {
        if (get(coords) == BoardField.EMPTY) {
            fields.put(coords, sign);
            return true;
        }
        return false;
    }
}
