package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Lazer;

public class LazerViewer extends ElementViewer{
    public LazerViewer(Lazer lazer) { super(lazer); }

    @Override
    public void draw(GUI gui) {
        gui.drawLazer(getModel());
    }
}
