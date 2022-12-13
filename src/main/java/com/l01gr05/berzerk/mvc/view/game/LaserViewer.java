package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Laser;

public class LaserViewer extends ElementViewer{
    public LaserViewer(Laser laser) { super(laser); }

    @Override
    public void draw(GUI gui) {
        gui.drawLaser(getModel());
    }
}
