package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;

public class Agent extends Element {
    private final Position initialPosition;
    private char direction;
    private int score;
    private int lives;
    public Agent(Position p) {
        super(p);
        this.initialPosition = p;
        this.direction = 'N';
        this.score = 0;
        this.lives = 3;
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

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public boolean isDead() {
        return lives == 0;
    }
}