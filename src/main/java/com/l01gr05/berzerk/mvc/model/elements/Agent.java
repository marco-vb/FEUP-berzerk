package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.AgentNormal;
import com.l01gr05.berzerk.mvc.model.AgentState;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;

import java.util.List;

public class Agent extends Element {
    private final Position initialPosition;
    private char direction;
    private AgentState state = new AgentNormal();
    private PowerUp powerUp;
    public Agent(Position p) {
        super(p);
        this.initialPosition = p;
        this.direction = 'N';
        this.powerUp = null;
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

    public void setState(AgentState state) {
        this.state = state;
    }

    public AgentState getState() {
        return this.state;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public PowerUp getPowerUp() {
        return this.powerUp;
    }

    public void shoot(Game game, Arena arena) {
        state.shoot(game, arena);
    }
}