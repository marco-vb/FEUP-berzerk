package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.CanonViewer;
import com.l01gr05.berzerk.mvc.view.game.LazerViewer;

public class Lazer extends PowerUp {
    public Lazer(Position p) { super(p, "Lazer"); }

    @Override
    public LazerViewer getViewer() {
        return new LazerViewer(this);
    }
}
