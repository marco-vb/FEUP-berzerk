package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Wall;

public class WallViewer extends ElementViewer {
    public WallViewer(Wall wall) {
        super(wall);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawWall(getModel());
    }
}
