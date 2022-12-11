package com.l01gr05.berzerk.mvc.gui;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.control.*;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.*;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.model.menu.MenuSettings;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.mvc.view.*;
import com.l01gr05.berzerk.mvc.view.menu.MenuViewer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class LanternaGUITest {
    private Game game;
    private LanternaGUI gui;
    private Screen screen;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    void testCreateScreen() throws IOException {
        Terminal terminal = Mockito.mock(Terminal.class);
        Screen screen = Mockito.mock(Screen.class);
        gui.createScreen(terminal);
        Mockito.verify(screen, Mockito.times(1)).startScreen();
        Mockito.verify(screen, Mockito.times(1)).setCursorPosition(null);
        Mockito.verify(screen, Mockito.times(1)).doResizeIfNecessary();
    }
}
