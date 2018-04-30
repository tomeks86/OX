package edu.tseidler.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FieldsTest {
    public static final Coordinates DEFAULT_COORDINATES = new Coordinates(-1, -1);
    private Fields fields;

    @BeforeMethod
    public void setUp() {
        fields = new Fields();
    }

    @Test
    public void shouldReturnDefaultLastCoordsAndMark() {
        Coordinates lastCoords = fields.getLastCoords();
        BoardField lastMark = fields.getLastMark();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(lastCoords, DEFAULT_COORDINATES);
        sa.assertEquals(lastMark, BoardField.EMPTY);
        sa.assertAll();
    }

    @Test
    public void shouldReturnLastCoordinatesAndPosition() {
        Coordinates coords = new Coordinates(1, 2);
        BoardField mark = BoardField.O;

        fields.put(coords, mark);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(fields.getLastCoords(), coords);
        sa.assertEquals(fields.getLastMark(), BoardField.O);
        sa.assertAll();
    }

    @Test
    public void shouldReturnLastCoordinatesAndPositionAfterMultipleInsertions() {
        fields.put(new Coordinates(1,3), BoardField.X);
        fields.put(new Coordinates(2,4), BoardField.O);
        fields.put(new Coordinates(0,2), BoardField.X);
        fields.put(new Coordinates(4,2), BoardField.O);
        Coordinates coords = new Coordinates(1, 2);
        BoardField mark = BoardField.X;

        fields.put(coords, mark);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(fields.getLastCoords(), coords);
        sa.assertEquals(fields.getLastMark(), BoardField.X);
        sa.assertAll();
    }
}
