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
        Assertions.assertNotNull(arena);
    }

    @Test
    void testLevelReader() throws IOException {
        arenaLoader = new ArenaLoader(level, game);
        List<String> lines = arenaLoader.readArenaFile(level);
        boolean height = lines.size() == Game.HEIGHT;
        boolean width = lines.get(0).length() == Game.WIDTH;
        Assertions.assertTrue(height && width);
    }
}
