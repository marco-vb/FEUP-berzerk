package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Key;
import com.l01gr05.berzerk.mvc.model.elements.Tower;
import com.l01gr05.berzerk.mvc.model.elements.Wall;
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
}
