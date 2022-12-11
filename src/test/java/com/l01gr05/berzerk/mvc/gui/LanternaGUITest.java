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
    void testLoadFont() throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = gui.loadSquareFont();
        Assertions.assertNotNull(fontConfig);
    }

    @Test
    void testRefresh() throws IOException {
        gui.refresh();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void testClear() throws IOException {
        gui.clear();
        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    void testClose() throws IOException {
        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    void testGetInput() throws IOException {
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        List<KeyType> keyTypes = List.of(KeyType.ArrowUp, KeyType.ArrowDown, KeyType.ArrowLeft, KeyType.ArrowRight, KeyType.Enter, KeyType.Escape);
        List<GUI.INPUT> inputs = List.of(GUI.INPUT.UP, GUI.INPUT.DOWN, GUI.INPUT.LEFT, GUI.INPUT.RIGHT, GUI.INPUT.ENTER, GUI.INPUT.QUIT);
        boolean isEqual = true;
        for (KeyType keyType : keyTypes) {
            Mockito.when(keyStroke.getKeyType()).thenReturn(keyType);
            Mockito.when(screen.pollInput()).thenReturn(keyStroke);
            GUI.INPUT input = gui.getInput();
            isEqual = isEqual && input == inputs.get(keyTypes.indexOf(keyType));
        }
        Assertions.assertTrue(isEqual);
    }

    @Test
    void testDrawAgent() {
        Agent agent = new Agent(new Position(1, 1));
        gui.drawAgent(agent);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "A");
    }

    @Test
    void testDrawWall() {
        Wall wall = new Wall(new Position(1, 1));
        gui.drawWall(wall);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "#");
    }

    @Test
    void testDrawExit() {
        Exit exit = new Exit(new Position(1, 1));
        gui.drawExit(exit);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, " ");
    }

    @Test
    void testDrawEnemy() {
        Enemy enemy = new Enemy(new Position(1, 1));
        gui.drawEnemy(enemy);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "E");
    }

    @Test
    void testDrawBullet() {
        Bullet bullet = new AgentBullet(new Position(1, 1), 'N');
        Bullet bullet2 = new EnemyBullet(new Position(1, 1), 'N');
        gui.drawBullet(bullet);
        gui.drawBullet(bullet2);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED);
        Mockito.verify(graphics, Mockito.times(2)).putString(1, 1, ".");
    }

    @Test
    void testDrawKey() {
        Key key = new Key(new Position(1, 1));
        gui.drawKey(key);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.CYAN_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "K");
    }

    @Test
    void testDrawTower() {
        Tower tower = new Tower(new Position(1, 1));
        gui.drawTower(tower);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED);
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 1, "T");
    }

    @Test
    void testDrawMenuStart() {
        Menu menuStart = new MenuStart();
        int x = Game.WIDTH / 2 - menuStart.getTitle().length() / 2 - 1;
        int y = Game.HEIGHT / 2 - menuStart.getOptions().size() / 2 - 1;
        gui.drawMenu(menuStart);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(x, y, menuStart.getTitle());
        Mockito.verify(graphics, Mockito.atLeast(1)).setForegroundColor(TextColor.ANSI.WHITE);
    }

    @Test
    void testDrawMenuSettings() {
        Menu menu = new MenuSettings();
        int x = Game.WIDTH / 2 - menu.getTitle().length() / 2 - 1;
        int y = Game.HEIGHT / 2 - menu.getOptions().size() / 2 - 1;
        gui.drawMenu(menu);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(x, y, menu.getTitle());
        Mockito.verify(graphics, Mockito.atLeast(1)).setForegroundColor(TextColor.ANSI.WHITE);
    }

    @Test
    void testDrawStats() {
        Arena arena = Mockito.mock(Arena.class);
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getScore()).thenReturn(42);
        Mockito.when(game.getLives()).thenReturn(3);
        gui.drawStats(arena, game);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        Mockito.verify(graphics, Mockito.times(1)).putString(0, Game.HEIGHT+1, "Score: 42");
        StringBuilder lives = new StringBuilder();
        for (int i = 0; i < game.getLives(); i++)
            lives.append("A");
        Mockito.verify(graphics, Mockito.times(1)).putString(Game.WIDTH - 10, Game.HEIGHT + 1, "Lives: AAA");
    }
}
