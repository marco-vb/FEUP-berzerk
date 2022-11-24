package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;

public class Agent extends Element {
    private char direction;
    public Agent(Position p) {
        super(p);
        this.direction = 'N';
    }

    @Override
    public AgentViewer getViewer() {
        return new AgentViewer(this);
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}