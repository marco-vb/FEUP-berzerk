package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import com.l01gr05.berzerk.mvc.view.game.ExitViewer;

public class Exit extends Element {
    public Exit(Position p) {
        super(p);
    }

    @Override
    public ElementViewer getViewer() {
        return new ExitViewer(this);
    }
}
