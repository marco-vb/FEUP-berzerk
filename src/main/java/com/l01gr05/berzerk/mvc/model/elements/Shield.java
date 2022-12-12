package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import com.l01gr05.berzerk.mvc.view.game.ShieldViewer;

public class Shield extends PowerUp {
    public Shield(Position p) {
        super(p,"Shield");
    }

    @Override
    public ShieldViewer getViewer() {
        return new ShieldViewer(this);
    }

}

