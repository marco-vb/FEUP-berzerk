package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;

public class AgentNormal implements AgentState {
    @Override
    public void shoot(Game game, Arena arena) {
        game.playShootSound();
        Agent agent = arena.getAgent();
        AgentBullet bullet = new AgentBullet(agent.getPosition(), agent.getDirection());
        arena.addElement(bullet);
    }
}

