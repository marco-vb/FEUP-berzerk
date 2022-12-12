package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.control.MoveStrategy;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.EnemyViewer;

public abstract class Enemy extends Element {
    private MoveStrategy moveStrategy;
    public Enemy(Position p) {
        super(p);
        this.moveStrategy = createMoveStrategy();
    }

    @Override
    public EnemyViewer getViewer() {
        return new EnemyViewer(this);
    }

    protected abstract MoveStrategy createMoveStrategy();

    public void move(Arena arena) {
        moveStrategy.move(this, arena);
    }
}
