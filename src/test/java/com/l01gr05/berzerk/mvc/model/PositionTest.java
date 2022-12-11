package com.l01gr05.berzerk.mvc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(1, 1);
    }

    @Test
    public void testDefault() {
        Assertions.assertEquals(1, position.getX());
        Assertions.assertEquals(1, position.getY());
    }

    @Test
    public void up() {
        Assertions.assertEquals(new Position(1, 0), position.getUp());
    }

    @Test
    public void down() {
        Assertions.assertEquals(new Position(1, 2), position.getDown());
    }

    @Test
    public void left() {
        Assertions.assertEquals(new Position(0, 1), position.getLeft());
    }

    @Test
    public void right() {
        Assertions.assertEquals(new Position(2, 1), position.getRight());
    }

    @Test
    public void testEquals() {
        Assertions.assertEquals(new Position(1, 1), position);
    }

    @Test
    public void random() {
        Assertions.assertNotEquals(new Position(1, 1), position.getRandom());
    }
}
