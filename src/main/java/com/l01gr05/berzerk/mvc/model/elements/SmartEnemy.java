package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.control.MoveStrategy;
import com.l01gr05.berzerk.mvc.control.SmartStrategy;
import com.l01gr05.berzerk.mvc.model.Position;

public class SmartEnemy extends Enemy {
    public SmartEnemy(Position p) {
        super(p);
    }

    @Override
    public MoveStrategy createMoveStrategy() {
        return new SmartStrategy();
    }
}
