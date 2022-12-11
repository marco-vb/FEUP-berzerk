package com.l01gr05.berzerk.mvc.state;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.mvc.control.game.ArenaController;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.view.game.ArenaViewer;
import com.l01gr05.berzerk.states.GameState;
import com.l01gr05.berzerk.states.State;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StateTest {
    private Arena arena;
    private LanternaGUI gui;
    private Game game;
    private State state;
    private ArenaController arenaController;
    private ArenaViewer arenaViewer;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getInput()).thenReturn(GUI.INPUT.NONE);
        game = Mockito.mock(Game.class);
        arena = Mockito.mock(Arena.class);
        arenaController = Mockito.mock(ArenaController.class);
        arenaViewer = Mockito.mock(ArenaViewer.class);
        state = new GameState(arena, arenaController, arenaViewer);
    }

    @Test
    public void testGetModel() {
        Assertions.assertEquals(arena, state.getModel());
    }

    @Test
    public void testUpdate() throws IOException {
        state.update(game, gui);
        Mockito.verify(arenaController).update(game, GUI.INPUT.NONE);
        Mockito.verify(arenaViewer).draw(gui, game);
    }
}
