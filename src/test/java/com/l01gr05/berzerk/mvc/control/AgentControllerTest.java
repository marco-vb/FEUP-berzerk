package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgentControllerTest {
    private AgentController agentController;
    private Agent agent;
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        agent = new Agent(new Position(1, 1));
        arena = new Arena(game);
        Exit exit = new Exit(new Position(1, 3));
        arena.addElement(agent);
        arena.addElement(exit);
        arena.addElement(new Key(new Position(2, 1)));
        agentController = new AgentController(arena);
    }

    @Test
    void testMoveUp() throws IOException {
        agentController.update(game, GUI.INPUT.UP);
        boolean isAgentMoved = agent.getPosition().equals(new Position(1, 0));
        boolean isDirectionChanged = agent.getDirection() == 'N';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testMoveDown() throws IOException {
        agentController.update(game, GUI.INPUT.DOWN);
        boolean isAgentMoved = agent.getPosition().equals(new Position(1, 2));
        boolean isDirectionChanged = agent.getDirection() == 'S';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testMoveRight() throws IOException {
        agentController.update(game, GUI.INPUT.RIGHT);
        boolean isAgentMoved = agent.getPosition().equals(new Position(2, 1));
        boolean isDirectionChanged = agent.getDirection() == 'E';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testMoveLeft() throws IOException {
        agentController.update(game, GUI.INPUT.LEFT);
        boolean isAgentMoved = agent.getPosition().equals(new Position(0, 1));
        boolean isDirectionChanged = agent.getDirection() == 'W';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testNotMove() throws IOException {
        agentController.update(game, GUI.INPUT.NONE);
        boolean isAgentMoved = agent.getPosition().equals(new Position(1, 1));
        boolean isDirectionChanged = agent.getDirection() == 'N';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testShoot1() throws IOException {
        agentController.update(game, GUI.INPUT.SHOOT);
        assertEquals(1, arena.getBullets().size());
    }

    @Test
    void testShoot2() throws IOException {
        agent.setPowerUp(new Shield(new Position(1, 1)));
        game.setPowerUp(agent.getPowerUp());
        agentController.update(game, GUI.INPUT.ACTIVATE);
        agentController.update(game, GUI.INPUT.SHOOT);
        assertEquals(1, arena.getBullets().size());
    }

    @Test
    void testShoot3() throws IOException {
        agent.setPowerUp(new Cannon(new Position(1, 1)));
        game.setPowerUp(agent.getPowerUp());
        agentController.update(game, GUI.INPUT.ACTIVATE);
        agentController.update(game, GUI.INPUT.SHOOT);
        assertEquals(4, arena.getBullets().size());
    }

    @Test
    void testShoot4() throws IOException {
        arena.addElement(new Wall(new Position(1, 0)));
        agent.setPowerUp(new Laser(new Position(1, 2)));
        game.setPowerUp(agent.getPowerUp());
        agentController.update(game, GUI.INPUT.ACTIVATE);
        agentController.update(game, GUI.INPUT.SHOOT);
        assertEquals(1, arena.getBullets().size());
    }

    @Test
    void moveUpWithWall() throws IOException {
        arena.addElement(new Wall(new Position(1, 0)));
        agentController.update(game, GUI.INPUT.UP);
        Mockito.verify(game, Mockito.times(1)).decreaseLives();
        assertEquals(agent.getPosition(), agent.getInitialPosition());
    }

    @Test
    void moveToExit() throws IOException {
        agentController.update(game, GUI.INPUT.DOWN);
        agentController.update(game, GUI.INPUT.DOWN);
        Mockito.verify(game, Mockito.times(1)).nextLevel();
    }

    @Test
    void moveToExitWithWall() throws IOException {
        arena.addElement(new Wall(new Position(1, 2)));
        agentController.update(game, GUI.INPUT.DOWN);
        assertEquals(agent.getPosition(), agent.getInitialPosition());
    }
}
