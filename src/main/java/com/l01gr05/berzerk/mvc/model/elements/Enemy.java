package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.EnemyViewer;

public class Enemy extends Element {
    public Enemy(Position p) {super(p);}

    @Override
    public EnemyViewer getViewer() {
        return new EnemyViewer(this);
    }
}
