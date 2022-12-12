package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.*;
import com.l01gr05.berzerk.mvc.control.*;
import com.l01gr05.berzerk.mvc.model.*;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.view.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class ArenaLoaderTest {
    private ArenaLoader arenaLoader;
    private int level;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        level = 10;
    }

    @Test
    void testLoadArena() throws IOException {
        arenaLoader = new ArenaLoader(level, game);
        Arena arena = arenaLoader.load();
        boolean isArenaLoaded = arena != null;
        boolean arenaHasAgent = arena.getAgent() != null;
        boolean arenaHasEnemies = arena.getEnemies().size() > 0;
        boolean arenaHasWalls = arena.getWalls().size() > 0;
        boolean arenaHasExits = arena.getExits().size() > 0;
        boolean arenaHasKey = arena.getKey() != null;
        boolean arenaHasTower = arena.getTowers().size() > 0;
        Assertions.assertTrue(isArenaLoaded && arenaHasAgent && arenaHasEnemies && arenaHasWalls && arenaHasExits && arenaHasKey && arenaHasTower);
    }
}
