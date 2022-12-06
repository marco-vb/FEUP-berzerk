package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.BulletViewer;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;

public abstract class Bullet extends Element {
    private final char direction;
    public Bullet(Position p, char direction) {
        super(p);
        this.direction = direction;
    }

    public char getDirection() {
        return direction;
    }

    @Override
    public abstract ElementViewer getViewer();
}
