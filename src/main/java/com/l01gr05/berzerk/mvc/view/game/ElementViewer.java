package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Element;

public abstract class ElementViewer {
    private final Element element;

    public ElementViewer(Element element) {
        this.element = element;
    }

    public Element getModel() {
        return element;
    }

    public abstract void draw(GUI gui);
}
