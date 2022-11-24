package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;

public class EnemyViewer extends ElementViewer {
    public EnemyViewer(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawEnemy(getModel());
    }
}
