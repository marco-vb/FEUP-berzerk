package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Cannon;

public class CannonViewer extends ElementViewer {
    public CannonViewer(Cannon cannon) { super(cannon); }

    @Override
    public void draw(GUI gui) {
        gui.drawCannon(getModel());
    }
}
