package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.BulletViewer;

public class AgentBullet extends Bullet {
    public AgentBullet(Position p, char direction) {
        super(p, direction);
    }

    @Override
    public BulletViewer getViewer() {
        return new BulletViewer(this);
    }
}
