package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
import com.l01gr05.berzerk.mvc.control.game.BulletController;
import com.l01gr05.berzerk.mvc.control.game.TowerController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.elements.*;
import com.l01gr05.berzerk.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TowerControllerTest {
    private Arena arena;
    private Game game;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = Mockito.mock(Game.class);
        arena = new Arena(game);
    }

    @Test
    void testShoot() throws IOException {   // Tests in if at least one bullet is added to the arena in 1000 iterations
        Tower tower = new Tower(new Position(1, 1));
        arena.addElement(tower);
        TowerController towerController = new TowerController(arena);
        for (int i = 0; i < 1000; i++) towerController.update(game, GUI.INPUT.SHOOT);
        assertNotEquals(0, arena.getBullets().size());
    }
}
