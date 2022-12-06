package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
import com.l01gr05.berzerk.mvc.control.game.BulletController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.model.elements.Exit;
import com.l01gr05.berzerk.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulletControllerTest {
    private BulletController bulletController;
    private Bullet bullet;
    private Arena arena;
    private Exit exit;
    private Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = Mockito.mock(Game.class);
        arena = new ArenaLoader(1).load();
        game.setState(new GameState(arena));
        exit = new Exit(new Position(9, 9));
        arena.addElement(exit);
    }

    @Test
    void testShootUp() {
        bullet = new AgentBullet(new Position(1, 1), 'N');
        arena.addBullet(bullet);
        bulletController = new BulletController(arena);


        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(1, 0), bullet.getPosition());
    }

    @Test
    void testShootDown() {
        bullet = new AgentBullet(new Position(1, 1), 'S');
        arena.addBullet(bullet);
        bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        System.out.printf("Bullet position: %s, %s", bullet.getPosition().getX(), bullet.getPosition().getY());
        assertEquals(new Position(1, 2), bullet.getPosition());
    }

    @Test
    void testShootRight() {
        bullet = new AgentBullet(new Position(1, 1), 'E');
        arena.addBullet(bullet);
        bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(2, 1), bullet.getPosition());
    }

    @Test
    void testShootLeft() {
        bullet = new AgentBullet(new Position(1, 1), 'W');
        arena.addBullet(bullet);
        bulletController = new BulletController(arena);

        bulletController.update(game, GUI.INPUT.UP);
        assertEquals(new Position(0, 1), bullet.getPosition());
    }
}
