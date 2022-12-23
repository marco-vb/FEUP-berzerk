package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.mvc.view.menu.MenuViewer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuViewerTest {
    private Game game;
    private GUI gui;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void testMenuDraw() throws IOException {
        Menu menu = new MenuStart();
        MenuViewer menuViewer = new MenuViewer(menu);
        menuViewer.draw(gui, game);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).drawMenu(menu);
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
