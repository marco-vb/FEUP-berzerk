package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.KeyViewer;

public class Key extends Element {
    public Key(Position p) {
        super(p);
    }

    @Override
    public KeyViewer getViewer() {
        return new KeyViewer(this);
    }
}


