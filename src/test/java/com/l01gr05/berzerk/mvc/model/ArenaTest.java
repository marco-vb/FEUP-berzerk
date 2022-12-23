package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaTest {
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
    }

    @Test
    void testIsTower() throws IOException {
        arena = new Arena(game);
        Tower tower = new Tower(new Position(1, 1));
        arena.addElement(tower);
        Assertions.assertTrue(arena.isTower(new Position(1, 1)));
    }

    @Test
    void testIsAgent() throws IOException {
        arena = new Arena(game);
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        Assertions.assertTrue(arena.isAgent(new Position(1, 1)));
    }

    @Test
    void testIsNotAgent() throws IOException {
        arena = new Arena(game);
        Agent agent = new Agent(new Position(1, 1));
        arena.addElement(agent);
        Assertions.assertFalse(arena.isAgent(new Position(2, 2)));
    }

    @Test
    void testNoKey() throws IOException {
        arena = new Arena(game);
        Assertions.assertFalse(arena.isKey(new Position(1, 1)));
    }

    @Test
    void testKey() throws IOException {
        arena = new Arena(game);
        Key key = new Key(new Position(1, 1));
        arena.addElement(key);
        Assertions.assertTrue(arena.isKey(new Position(1, 1)));
    }

    @Test
    void testKey2() throws IOException {
        arena = new Arena(game);
        Key key = new Key(new Position(1, 1));
        arena.addElement(key);
        Assertions.assertFalse(arena.isKey(new Position(2, 2)));
    }

    @Test
    void testGetWall() throws IOException {
        arena = new Arena(game);
        Wall wall = new Wall(new Position(1, 1));
        arena.addElement(wall);
        Assertions.assertEquals(wall, arena.getWall(new Position(1, 1)));
    }

    @Test
    void testGetGame() throws IOException {
        arena = new Arena(game);
        Assertions.assertEquals(game, arena.getGame());
    }

    @Test
    void testGetPowerUp() throws IOException {
        arena = new Arena(game);
        PowerUp powerUp = new Laser(new Position(1, 1));
        arena.addElement(powerUp);
        Assertions.assertEquals(powerUp, arena.getPowerUp(new Position(1, 1)));
        Assertions.assertEquals(powerUp, arena.getPowerUps().get(0));
    }

    @Test
    void testIsPowerUp() throws IOException {
        arena = new Arena(game);
        PowerUp powerUp = new Laser(new Position(1, 1));
        arena.addElement(powerUp);
        Assertions.assertTrue(arena.isPowerUp(new Position(1, 1)));
    }

    @Test
    void testIsPowerUp2() throws IOException {
        arena = new Arena(game);
        PowerUp powerUp = new Laser(new Position(1, 1));
        arena.addElement(powerUp);
        Assertions.assertFalse(arena.isPowerUp(new Position(2, 2)));
    }

    @Test
    void testRemovePowerUp() throws IOException {
        arena = new Arena(game);
        PowerUp powerUp1 = new Shield(new Position(1, 1));
        PowerUp powerUp2 = new Cannon(new Position(2, 2));
        arena.addElement(powerUp1);
        arena.addElement(powerUp2);
        arena.removePowerUp(new Position(1, 1));
        arena.removePowerUp(new Position(2, 2));
        Assertions.assertEquals(0, arena.getPowerUps().size());
    }

    @Test
    void testSetOpen() throws IOException {
        arena = new Arena(game);
        arena.addElement(new Wall(new Position(1, 1)));
        arena.addElement(new Exit(new Position(1, 1)));
        arena.setOpen();
        Assertions.assertEquals(0, arena.getWalls().size());
    }
}
