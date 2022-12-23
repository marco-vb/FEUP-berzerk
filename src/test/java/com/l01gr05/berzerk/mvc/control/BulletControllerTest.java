package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.BulletController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class BulletControllerTest {
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = Mockito.mock(Game.class);
        arena = new Arena(game);
    }

    @Test
    void testShootUp() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        AgentBullet agentBullet = new AgentBullet(new Position(1, 1), 'N');
        arena.addElement(agentBullet);
        BulletController bulletController = new BulletController(arena);
        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(1, 0), agentBullet.getPosition());
    }

    @Test
    void testShootDown() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        AgentBullet agentBullet = new AgentBullet(new Position(1, 1), 'S');
        arena.addElement(agentBullet);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        System.out.printf("Bullet position: %s, %s", agentBullet.getPosition().getX(), agentBullet.getPosition().getY());
        assertEquals(new Position(1, 2), agentBullet.getPosition());
    }

    @Test
    void testShootRight() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        AgentBullet agentBullet = new AgentBullet(new Position(1, 1), 'E');
        arena.addElement(agentBullet);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(2, 1), agentBullet.getPosition());
    }

    @Test
    void testShootLeft() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        AgentBullet agentBullet = new AgentBullet(new Position(1, 1), 'W');
        arena.addElement(agentBullet);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(0, 1), agentBullet.getPosition());
    }

    @Test
    void testShootEnemy() {
        Enemy enemy = new DumbEnemy(new Position(1, 1));
        Agent agent = new Agent(new Position(1, 3));
        arena.addElement(agent);
        arena.addElement(enemy);
        AgentBullet agentBullet = new AgentBullet(new Position(1, 3), 'N');
        arena.addElement(agentBullet);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        bulletController.update(game, GUI.INPUT.UP);

        assertEquals(0, arena.getEnemies().size());
    }

    @Test
    void testShootAgent() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        EnemyBullet enemyBullet = new EnemyBullet(new Position(1, 2), 'N');
        arena.addElement(enemyBullet);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        Mockito.verify(game, Mockito.times(1)).decreaseLives();
    }

    @Test
    void testShootWall() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        AgentBullet agentBullet = new AgentBullet(new Position(1, 1), 'N');
        arena.addElement(agentBullet);
        Wall wall = new Wall(new Position(1, 0));
        arena.addElement(wall);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(0, arena.getBullets().size());
    }

    @Test
    void testShootAgentWithShield() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        EnemyBullet enemyBullet = new EnemyBullet(new Position(1, 2), 'N');
        arena.addElement(enemyBullet);
        agent.setPowerUp(new Shield(new Position(1, 1)));
        agent.getPowerUp().setEnabled(true);
        BulletController bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        Mockito.verify(game, Mockito.times(0)).decreaseLives();
        boolean noBullets = arena.getBullets().size() == 0;
        boolean noPowerUps = agent.getPowerUp() == null;
        assertTrue(noBullets && noPowerUps);
    }

    @Test
    void testGameOver() {
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        EnemyBullet enemyBullet = new EnemyBullet(new Position(1, 2), 'N');
        arena.addElement(enemyBullet);
        BulletController bulletController = new BulletController(arena);
        Mockito.when(game.isGameOver()).thenReturn(true);
        bulletController.update(game, GUI.INPUT.UP);
        Mockito.verify(game, Mockito.times(1)).showDeathMenu();
        Mockito.verify(game, Mockito.times(1)).setPowerUp(null);
        assertNull(agent.getPowerUp());
    }
}
