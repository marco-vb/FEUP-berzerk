package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import com.l01gr05.berzerk.mvc.view.game.WallViewer;

public class Wall extends Element {
    public Wall(Position p) {super(p);}

    @Override
    public WallViewer getViewer() {
        return new WallViewer(this);
    }
}

