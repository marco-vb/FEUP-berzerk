package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaLoaderTest {
    private int level;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        level = 10;
    }

    @Test
    void testLoadArena() throws IOException {
        ArenaLoader arenaLoader = new ArenaLoader(level, game);
        Arena arena = arenaLoader.load();
        assert arena != null;
        boolean arenaHasAgent = arena.getAgent() != null;
        boolean arenaHasEnemies = arena.getEnemies().size() > 0;
        boolean arenaHasWalls = arena.getWalls().size() > 0;
        boolean arenaHasExits = arena.getExits().size() > 0;
        boolean arenaHasKey = arena.getKey() != null;
        boolean arenaHasTower = arena.getTowers().size() > 0;
        Assertions.assertTrue(arenaHasAgent && arenaHasEnemies && arenaHasWalls && arenaHasExits && arenaHasKey && arenaHasTower);
    }
}
