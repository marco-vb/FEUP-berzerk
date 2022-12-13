package com.l01gr05.berzerk.mvc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

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
        Assertions.assertTrue(position.equals(new Position(1, 1)));
    }

    @Test
    public void testEqualsNull() {
        Assertions.assertFalse(position.equals(null));
    }

    @Test
    public void random() {
        Position random = position.getRandom();
        boolean different = random.getX() != position.getX() || random.getY() != position.getY();
        boolean xIsInRange = random.getX() >= 0 && random.getX() < 3;
        boolean yIsInRange = random.getY() >= 0 && random.getY() < 3;
        Assertions.assertTrue(different && xIsInRange && yIsInRange);
    }

    @Test
    public void closest1() {
        Position closest = position.getClosest(new Position(2, 2));
        Assertions.assertEquals(new Position(2, 2), closest);
    }

    @Test
    public void closest2() {
        Position closest = position.getClosest(new Position(0, 0));
        Assertions.assertEquals(new Position(0, 0), closest);
    }

    @Test
    public void closest3() {
        Position closest = position.getClosest(new Position(2, 0));
        Assertions.assertEquals(new Position(2, 0), closest);
    }

    @Test
    public void closest4() {
        Position closest = position.getClosest(new Position(0, 2));
        Assertions.assertEquals(new Position(0, 2), closest);
    }

    @Test
    public void testHashCode() {
        Assertions.assertEquals(Objects.hash(1, 1), position.hashCode());
    }
}
