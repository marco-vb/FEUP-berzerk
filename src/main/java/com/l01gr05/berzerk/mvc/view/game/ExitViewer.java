package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Exit;

public class ExitViewer extends ElementViewer {
    public ExitViewer(Exit exit) {
        super(exit);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawExit(getModel());
    }
}
