package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;

public class AgentCannon implements AgentState {
    @Override
    public void shoot(Game game, Arena arena) {
        game.playCannonSound();
        Position p = arena.getAgent().getPosition();
        arena.addElement(new AgentBullet(new Position(p.getX(), p.getY() - 1), 'N'));
        arena.addElement(new AgentBullet(new Position(p.getX(), p.getY() + 1), 'S'));
        arena.addElement(new AgentBullet(new Position(p.getX() - 1, p.getY()), 'W'));
        arena.addElement(new AgentBullet(new Position(p.getX() + 1, p.getY()), 'E'));
        arena.getAgent().setPowerUp(null);

        game.setPowerUp(null);
    }
}
