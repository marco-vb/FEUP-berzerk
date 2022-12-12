package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.control.DumbStrategy;
import com.l01gr05.berzerk.mvc.control.MoveStrategy;
import com.l01gr05.berzerk.mvc.model.Position;

public class DumbEnemy extends Enemy {
    public DumbEnemy(Position p) {
        super(p);
    }

    public MoveStrategy createMoveStrategy() {
        return new DumbStrategy();
    }
}
