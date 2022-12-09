package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import com.l01gr05.berzerk.mvc.view.game.TowerViewer;

public class Tower extends Element {
    public Tower(Position p) {
        super(p);
    }

    @Override
    public ElementViewer getViewer() {
        return new TowerViewer(this);
    }
}
