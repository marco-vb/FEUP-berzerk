package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;

import java.io.IOException;

public class AgentNormal implements AgentState {
    @Override
    public void shoot(Game game, Arena arena) {
        game.playShootSound();
        Agent agent = arena.getAgent();
        AgentBullet bullet = new AgentBullet(agent.getPosition(), agent.getDirection());
        arena.addElement(bullet);
    }
}
