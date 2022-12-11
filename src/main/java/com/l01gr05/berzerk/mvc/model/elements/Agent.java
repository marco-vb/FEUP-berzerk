package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;

import java.util.List;

public class Agent extends Element {
    private final Position initialPosition;
    private char direction;
    private PowerUp powerUp;
    public Agent(Position p) {
        super(p);
        this.initialPosition = p;
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

    public Position getInitialPosition() {
        return initialPosition;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

}