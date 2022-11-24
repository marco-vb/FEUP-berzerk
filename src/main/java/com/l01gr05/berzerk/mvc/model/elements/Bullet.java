package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.BulletViewer;

public class Bullet extends Element {
    private char direction;
    public Bullet(Position p, char direction) {
        super(p);
        this.direction = direction;
    }

    @Override
    public BulletViewer getViewer() {
        return new BulletViewer(this);
    }

    public char getDirection() {
        return direction;
    }
}
