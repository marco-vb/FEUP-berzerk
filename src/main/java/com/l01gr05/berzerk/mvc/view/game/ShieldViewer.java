package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Shield;

public class ShieldViewer extends ElementViewer{
    public ShieldViewer(Shield shield) {super(shield); }

    @Override
    public void draw(GUI gui) {
        gui.drawShield(getModel());
    }
}
