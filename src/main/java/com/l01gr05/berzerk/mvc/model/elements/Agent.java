package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;

public class Agent extends Element {
    public Agent(Position p) {super(p);}

    @Override
    public AgentViewer getViewer() {
        return new AgentViewer(this);
    }
}