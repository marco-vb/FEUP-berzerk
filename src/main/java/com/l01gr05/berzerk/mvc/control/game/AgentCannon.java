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
        arena.addElement(new AgentBullet(p, 'N'));
        arena.addElement(new AgentBullet(p, 'S'));
        arena.addElement(new AgentBullet(p, 'E'));
        arena.addElement(new AgentBullet(p, 'W'));
        arena.getAgent().setPowerUp(null);
        game.setPowerUp(null);
    }
}
