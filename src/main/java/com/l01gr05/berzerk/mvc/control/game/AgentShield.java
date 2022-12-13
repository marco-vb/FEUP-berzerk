package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;

public class AgentShield implements AgentState {
    @Override
    public void shoot(Game game, Arena arena) {
        Agent agent = arena.getAgent();
        AgentBullet bullet = new AgentBullet(agent.getPosition(), agent.getDirection());
        arena.addElement(bullet);
    }
}
