package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.LaserViewer;

public class Laser extends PowerUp {
    public Laser(Position p) { super(p, "Laser"); }

    @Override
    public LaserViewer getViewer() {
        return new LaserViewer(this);
    }
}
