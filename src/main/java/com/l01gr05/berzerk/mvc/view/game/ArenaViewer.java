package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Element;
import com.l01gr05.berzerk.mvc.view.Viewer;

import java.io.IOException;
import java.util.List;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        List<Element> elements = getModel().getElements();
        for (Element element : elements) {
            element.getViewer().draw(gui);
        }
        gui.refresh();
    }
}
