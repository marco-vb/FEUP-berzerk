package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.BulletViewer;

public class Bullet extends Element {

    public Bullet(Position p) {super(p);}

    @Override
    public BulletViewer getViewer() {
        return new BulletViewer(this);
    }
}
