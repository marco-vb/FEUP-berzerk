package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Element;
import com.l01gr05.berzerk.mvc.model.elements.Tower;

public class TowerViewer extends ElementViewer {
    public TowerViewer(Tower tower) {
        super(tower);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawTower(getModel());
    }
}

