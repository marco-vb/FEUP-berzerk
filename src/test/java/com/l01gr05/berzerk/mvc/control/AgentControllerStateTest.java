package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentControllerState;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Exit;
import com.l01gr05.berzerk.mvc.model.elements.Key;
import com.l01gr05.berzerk.mvc.model.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgentControllerStateTest {
    private AgentControllerState agentControllerState;
    private Agent agent;
    private Arena arena;
    private Exit exit;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        agent = new Agent(new Position(1, 1));
        arena = new Arena(game);
        exit = new Exit(new Position(1, 3));
        arena.addElement(agent);
        arena.addElement(exit);
        arena.addElement(new Key(new Position(2, 1)));
        agentControllerState = new AgentControllerState(arena);
    }

    @Test
    void testMoveUp() throws IOException {
        agentControllerState.update(game, GUI.INPUT.UP);
        boolean isAgentMoved = agent.getPosition().equals(new Position(1, 0));
        boolean isDirectionChanged = agent.getDirection() == 'N';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testMoveDown() throws IOException {
        agentControllerState.update(game, GUI.INPUT.DOWN);
        boolean isAgentMoved = agent.getPosition().equals(new Position(1, 2));
        boolean isDirectionChanged = agent.getDirection() == 'S';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testMoveRight() throws IOException {
        agentControllerState.update(game, GUI.INPUT.RIGHT);
        boolean isAgentMoved = agent.getPosition().equals(new Position(2, 1));
        boolean isDirectionChanged = agent.getDirection() == 'E';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testMoveLeft() throws IOException {
        agentControllerState.update(game, GUI.INPUT.LEFT);
        boolean isAgentMoved = agent.getPosition().equals(new Position(0, 1));
        boolean isDirectionChanged = agent.getDirection() == 'W';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testNotMove() throws IOException {
        agentControllerState.update(game, GUI.INPUT.NONE);
        boolean isAgentMoved = agent.getPosition().equals(new Position(1, 1));
        boolean isDirectionChanged = agent.getDirection() == 'N';
        assertTrue(isAgentMoved && isDirectionChanged);
    }

    @Test
    void testShoot() throws IOException {
        agentControllerState.update(game, GUI.INPUT.SHOOT);
        assertEquals(1, arena.getBullets().size());
    }

    @Test
    void moveUpWithWall() throws IOException {
        arena.addElement(new Wall(new Position(1, 0)));
        agentControllerState.update(game, GUI.INPUT.UP);
        Mockito.verify(game, Mockito.times(1)).decreaseLives();
        assertEquals(agent.getPosition(), agent.getInitialPosition());
    }

    @Test
    void moveToExit() throws IOException {
        agentControllerState.update(game, GUI.INPUT.DOWN);
        agentControllerState.update(game, GUI.INPUT.DOWN);
        Mockito.verify(game, Mockito.times(1)).nextLevel();
    }

    @Test
    void moveToExitWithWall() throws IOException {
        arena.addElement(new Wall(new Position(1, 2)));
        agentControllerState.update(game, GUI.INPUT.DOWN);
        assertEquals(agent.getPosition(), agent.getInitialPosition());
    }
}
