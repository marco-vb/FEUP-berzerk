package com.l01gr05.berzerk.mvc.model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getRandom() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                return getUp();
            case 1:
                return getDown();
            case 2:
                return getLeft();
            case 3:
                return getRight();
            default:
                return this;
        }
    }

    public Position getClosest(Position position) {
        int new_x = x;
        int new_y = y;
        if (position.getX() > x) {
            new_x++;
        } else if (position.getX() < x) {
            new_x--;
        }
        if (position.getY() > y) {
            new_y++;
        } else if (position.getY() < y) {
            new_y--;
        }

        return new Position(new_x, new_y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
