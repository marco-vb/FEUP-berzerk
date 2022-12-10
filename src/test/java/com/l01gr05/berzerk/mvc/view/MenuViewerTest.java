package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.mvc.view.menu.MenuViewer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuViewerTest {
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void testMenuDraw() throws IOException {
        menu = new MenuStart();
        MenuViewer menuViewer = new MenuViewer(menu);
        menuViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).drawMenu(menu);
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
