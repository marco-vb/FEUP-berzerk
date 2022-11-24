package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;

public abstract class Element {
    private Position position;
    private Viewer<Element> viewer;

    public Element(Position p) {
        this.position = p;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract ElementViewer getViewer();
}
