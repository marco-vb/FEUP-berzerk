package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Canon;

public class CanonViewer extends ElementViewer {
    public CanonViewer(Canon canon) { super(canon); }

    @Override
    public void draw(GUI gui) {
        gui.drawCanon(getModel());
    }
}
