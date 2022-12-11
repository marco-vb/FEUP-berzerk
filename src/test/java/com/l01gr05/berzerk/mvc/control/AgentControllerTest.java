package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
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

public class AgentControllerTest {
    private AgentController agentController;
    private Agent agent;
    private Arena arena;
    private Exit exit;
    private Game game;
    private Key key;
    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        agent = new Agent(new Position(1, 1));
        arena = new Arena(game);
        exit = new Exit(new Position(1, 3));
        arena.addElement(agent);
        arena.addElement(exit);
        arena.addElement(new Key(new Position(2, 1)));
        agentController = new AgentController(arena);
    }

    @Test
    void testMoveUp() throws IOException {
        agentController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(1, 0), agent.getPosition());
    }

    @Test
    void testMoveDown() throws IOException {
        agentController.update(game, GUI.INPUT.DOWN);
        assertEquals(new Position(1, 2), agent.getPosition());
    }

    @Test
    void testMoveRight() throws IOException {
        agentController.update(game, GUI.INPUT.RIGHT);
        assertEquals(new Position(2, 1), agent.getPosition());
    }

    @Test
    void testMoveLeft() throws IOException {
        agentController.update(game, GUI.INPUT.LEFT);
        assertEquals(new Position(0, 1), agent.getPosition());
    }

    @Test
    void testShoot() throws IOException {
        agentController.update(game, GUI.INPUT.SHOOT);
        assertEquals(1, arena.getBullets().size());
    }

    @Test
    void moveUpWithWall() throws IOException {
        arena.addElement(new Wall(new Position(1, 0)));
        agentController.update(game, GUI.INPUT.UP);
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
