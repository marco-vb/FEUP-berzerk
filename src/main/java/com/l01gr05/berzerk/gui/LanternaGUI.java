package com.l01gr05.berzerk.gui;

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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final Screen screen;
    private final int width;
    private final int height;
    private final Game game;

    public LanternaGUI(int width, int height, Game game) throws IOException, URISyntaxException, FontFormatException {
        this.width = width;
        this.height = height;
        this.game = game;
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 2);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    public INPUT getInput() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) {
            return INPUT.NONE;
        }
        KeyType keyType = keyStroke.getKeyType();

        switch (keyType) {
            case ArrowUp:
                return INPUT.UP;
            case ArrowDown:
                return INPUT.DOWN;
            case ArrowLeft:
                return INPUT.LEFT;
            case ArrowRight:
                return INPUT.RIGHT;
            case Escape:
                return INPUT.QUIT;
            case Enter:
                return INPUT.ENTER;
            case Character:
                if (keyStroke.getCharacter() == ' ') {
                    return INPUT.SHOOT;
                }
            default:
                return INPUT.NONE;
        }
    }

    @Override
    public void drawAgent(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), 'A', TextColor.ANSI.MAGENTA_BRIGHT);
    }

    @Override
    public void drawExit(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), ' ', TextColor.ANSI.WHITE);
    }

    @Override
    public void drawWall(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), '#', TextColor.ANSI.GREEN_BRIGHT);
    }

    @Override
    public void drawEnemy(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), 'E', TextColor.ANSI.RED_BRIGHT);
    }

    @Override
    public void drawBullet(Element model) {
        TextColor color = model instanceof AgentBullet ? TextColor.ANSI.YELLOW_BRIGHT : TextColor.ANSI.RED;
        draw(model.getPosition().getX(), model.getPosition().getY(), '.', color);
    }

    @Override
    public void drawKey(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), 'K', TextColor.ANSI.CYAN_BRIGHT);
    }

    @Override
    public void drawTower(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), 'T', TextColor.ANSI.RED);
    }

    @Override
    public void draw(int x, int y, char c, TextColor color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(color);
        textGraphics.putString(x, y, String.valueOf(c));
    }

    @Override
    public void drawMenu(Menu menu) {
        int x = getWidth() / 2 - menu.getTitle().length() / 2 - 1;
        int y = getHeight() / 2 - menu.getOptions().size() / 2 - 1;
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        textGraphics.putString(x, y, menu.getTitle());
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        for (int i = 0; i < menu.getOptions().size(); i++) {
            if (i == menu.getCurrentOption()) {
                textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                textGraphics.putString(x, y + i + 1, menu.getOptions().get(i));
                textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            } else {
                textGraphics.putString(x, y + i + 1, menu.getOptions().get(i));
            }
        }
    }

    @Override
    public void drawStats(Arena model) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        textGraphics.putString(0, getHeight() + 1, "Score: " + game.getScore());
        StringBuilder lives = new StringBuilder();
        for (int i = 0; i < game.getLives(); i++)
            lives.append("A");
        textGraphics.putString(getWidth() - 10, getHeight() + 1, "Lives: " + lives);
    }
}
