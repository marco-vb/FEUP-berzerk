package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Key;

public class KeyViewer extends ElementViewer {
    public KeyViewer(Key key) {
        super(key);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawKey(getModel());
    }
}

