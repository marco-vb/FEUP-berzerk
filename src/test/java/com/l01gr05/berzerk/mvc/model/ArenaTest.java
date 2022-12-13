package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.mvc.view.menu.MenuViewer;
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
    void testReturnsFalseIfNoKey() throws IOException {
        arena = new Arena(game);
        Assertions.assertFalse(arena.isKey(new Position(1, 1)));
    }

    @Test
    void testReturnsTrueIfKey() throws IOException {
        arena = new Arena(game);
        Key key = new Key(new Position(1, 1));
        arena.addElement(key);
        Assertions.assertTrue(arena.isKey(new Position(1, 1)));
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
}
