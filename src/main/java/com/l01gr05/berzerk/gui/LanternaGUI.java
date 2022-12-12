package com.l01gr05.berzerk.gui;

import com.googlecode.lanterna.TerminalPosition;
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
import java.util.Map;

import static com.l01gr05.berzerk.Game.BACKGROUND_COLOR;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(Screen screen) { //Only created for testing purposes
        this.screen = screen;
    }
    public LanternaGUI() throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(fontConfig);
        this.screen = createScreen(terminal);
    }
    public Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }
    public Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(Game.WIDTH + Game.STATS_WIDTH, Game.HEIGHT);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        terminalFactory.setTerminalEmulatorTitle("Berzerk");
        return terminalFactory.createTerminal();
    }
    public AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/Square-Regular.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public void clear() {
        screen.clear();
        drawSpace();
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
            case Tab:
                return INPUT.ACTIVATE;
            case Character:
                if (keyStroke.getCharacter() == ' ') {
                    return INPUT.SHOOT;
                }
            default:
                return INPUT.NONE;
        }
    }
    private void drawSpace() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Game.WIDTH + Game.STATS_WIDTH, Game.HEIGHT), ' ');
    }
    @Override
    public void draw(int x, int y, char c, TextColor color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        textGraphics.setForegroundColor(color);
        textGraphics.putString(x, y, String.valueOf(c));
    }
    @Override
    public void drawAgent(Element model) {
        Agent agent = (Agent) model;
        Map<Character, Character> directionToChar = Map.of('N', '%','S', '!','E', '$','W', '&');
        char c = directionToChar.get(agent.getDirection());
        draw(model.getPosition().getX(), model.getPosition().getY(), c, TextColor.Factory.fromString("#E22B5E"));
    }
    @Override
    public void drawExit(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), ' ', TextColor.ANSI.WHITE);
    }
    @Override
    public void drawWall(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), '#', TextColor.Factory.fromString("#820328"));
    }
    @Override
    public void drawEnemy(Element model) {
        if (model instanceof DumbEnemy) draw(model.getPosition().getX(), model.getPosition().getY(), '+', TextColor.Factory.fromString("#8158BD"));
        else draw(model.getPosition().getX(), model.getPosition().getY(), ')', TextColor.Factory.fromString("#8158BD"));
    }
    @Override
    public void drawBullet(Element model) {
        TextColor color = model instanceof AgentBullet ?
                TextColor.Factory.fromString("#E22B5E") : TextColor.Factory.fromString("#8158BD");
        draw(model.getPosition().getX(), model.getPosition().getY(), '.', color);
    }
    @Override
    public void drawKey(Element model) {
        if (model != null) draw(model.getPosition().getX(), model.getPosition().getY(), '*', TextColor.ANSI.YELLOW_BRIGHT);
    }
    @Override
    public void drawTower(Element model) {
        draw(model.getPosition().getX(), model.getPosition().getY(), '(', TextColor.Factory.fromString("#F2582A"));
    }

    @Override
    public void drawShield(Element model) {
        if (model != null) draw(model.getPosition().getX(), model.getPosition().getY(), '?', TextColor.ANSI.BLUE_BRIGHT);
    }

    @Override
    public void drawCanon(Element model) {
        if (model != null) draw(model.getPosition().getX(), model.getPosition().getY(), '?', TextColor.ANSI.BLUE_BRIGHT);
    }

    @Override
    public void drawLazer(Element model){
        if (model != null) draw(model.getPosition().getX(), model.getPosition().getY(), '?', TextColor.ANSI.BLUE_BRIGHT);
    }

    @Override
    public void drawMenu(Menu menu) {
        int x = (Game.WIDTH + Game.STATS_WIDTH) / 2 - menu.getTitle().length() / 2 - 1;
        int y = Game.HEIGHT / 2 - menu.getOptions().size() / 2 - 1;
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
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
    public void drawStats(Arena model, Game game) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        textGraphics.putString(Game.WIDTH + 2, 7, "Score: " + String.format("%03d", game.getScore()));
        textGraphics.putString(Game.WIDTH + 2, 9, "Power: ");
        textGraphics.setForegroundColor((game.isPowerUpActive()) ? TextColor.ANSI.BLUE_BRIGHT : TextColor.ANSI.WHITE_BRIGHT);
        textGraphics.putString(Game.WIDTH + 11, 9, (game.getPowerUp() == null) ? " " : game.getPowerUp().getType().substring(0,1));

        textGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        StringBuilder lives = new StringBuilder();
        for (int i = 0; i < game.getLives(); i++)
            lives.append("%");
        textGraphics.putString(Game.WIDTH + 2, 11, "Lives: " + lives);
    }
}
