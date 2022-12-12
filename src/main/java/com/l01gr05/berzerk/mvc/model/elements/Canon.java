package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.CanonViewer;

public class Canon extends PowerUp {
    public Canon(Position p) { super(p, "Canon"); }

    @Override
    public CanonViewer getViewer() {
        return new CanonViewer(this);
    }
}
