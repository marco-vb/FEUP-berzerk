package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;

public class AgentLaser implements AgentState {
    @Override
    public void shoot(Game game, Arena arena) {
        Agent agent = arena.getAgent();
        char direction = agent.getDirection();
        Position position = agent.getPosition();
        while (!arena.isWall(position)){
            arena.addElement(new AgentBullet(position, direction));
            if (direction == 'N') position = position.getUp();
            if (direction == 'S') position = position.getDown();
            if (direction == 'E') position = position.getRight();
            if (direction == 'W') position = position.getLeft();
        }
        agent.setPowerUp(null);
        game.setPowerUp(null);
        game.setIsPowerUpActive(false);
    }
}
