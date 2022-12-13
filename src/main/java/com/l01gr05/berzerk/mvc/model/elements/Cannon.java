package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.CannonViewer;

public class Cannon extends PowerUp {
    public Cannon(Position p) { super(p, "Cannon"); }

    @Override
    public CannonViewer getViewer() {
        return new CannonViewer(this);
    }
}
