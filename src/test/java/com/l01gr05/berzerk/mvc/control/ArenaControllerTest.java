package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
import com.l01gr05.berzerk.mvc.control.game.ArenaController;
import com.l01gr05.berzerk.mvc.control.game.BulletController;
import com.l01gr05.berzerk.mvc.control.game.EnemyController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArenaControllerTest {
    private Game game;
    private Arena arena;
    private Agent agent;
    private Enemy enemy;
    private Exit exit;
    private Key key;
    private Wall wall;
    private ArenaController arenaController;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        arena = new Arena(game);
        agent = new Agent(new Position(1, 1));
        enemy = new DumbEnemy(new Position(1, 2));
        exit = new Exit(new Position(1, 3));
        key = new Key(new Position(2, 1));
        wall = new Wall(new Position(0, 1));
        arena.addElement(agent);
        arena.addElement(exit);
        arena.addElement(key);
        arena.addElement(wall);
        arena.addElement(enemy);
        arenaController = new ArenaController(arena);
    }

    @Test
    void testUpdateWithQuit() throws IOException {
        arenaController.update(game, GUI.INPUT.QUIT);
        Mockito.verify(game).showPauseMenu();
    }

    @Test
    void testUpdateWithAgentController() throws IOException {
        arenaController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(1, 0), agent.getPosition());
    }

    @Test
    void testUpdateWithBulletController() throws IOException {
        arenaController.update(game, GUI.INPUT.SHOOT);
        assertNotEquals(0, arena.getBullets().size());
    }

    @Test
    void testUpdateWithEnemyController() throws IOException {
        arenaController.update(game, GUI.INPUT.DOWN);
        assertEquals(1, arena.getEnemies().size());
    }
}
