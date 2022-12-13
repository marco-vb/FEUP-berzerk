package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;

public class AgentCannon implements AgentState {
    @Override
    public void shoot(Game game, Arena arena) {
        Agent agent = arena.getAgent();
        arena.addElement(new AgentBullet(agent.getPosition(), 'N'));
        arena.addElement(new AgentBullet(agent.getPosition(), 'S'));
        arena.addElement(new AgentBullet(agent.getPosition(), 'E'));
        arena.addElement(new AgentBullet(agent.getPosition(), 'W'));
        agent.setPowerUp(null);
        game.setPowerUp(null);
    }
}
