package com.l01gr05.berzerk.mvc;


import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.states.State;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameTest {
    private Game game;
    private LanternaGUI gui;

    @BeforeEach
    public void setUp() {
        gui = Mockito.mock(LanternaGUI.class);
        game = new Game(gui);
    }

    @Test
    public void testSetState() {
        State state = Mockito.mock(State.class);
        game.setState(state);
        Assertions.assertEquals(state, game.getState());
    }

    @Test
    public void testSetScore() {
        game.setScore(10);
        Assertions.assertEquals(10, game.getScore());
    }

    @Test
    public void testDecreaseLives() {
        game.decreaseLives();
        Assertions.assertEquals(2, game.getLives());
    }

    @Test
    public void testGameOver() {
        game.decreaseLives();
        boolean notOver = game.isGameOver();
        game.decreaseLives();
        game.decreaseLives();
        boolean over = game.isGameOver();
        Assertions.assertTrue(over && !notOver);
    }

    @Test
    public void testStartGame() throws IOException {
        game.startGame();
        boolean score = game.getScore() == 0;
        boolean lives = game.getLives() == 3;
        boolean level = game.getLevel() == 1;
        boolean state = game.getState() != null;
        Assertions.assertTrue(score && lives && level && state);
    }

    @Test
    public void testNextLevel() throws IOException {
        game.nextLevel();
        boolean score = game.getScore() == 100;
        boolean lives = game.getLives() == 3;
        boolean level = game.getLevel() == 2;
        Assertions.assertTrue(score && lives && level);
    }

    @Test
    public void testShowStartMenu() throws IOException {
        game.showStartMenu();
        Assertions.assertNotNull(game.getState());
    }

    @Test
    public void testShowSettingsMenu() throws IOException {
        game.showSettings();
        Assertions.assertNotNull(game.getState());
    }

    @Test
    public void testExit() throws IOException {
        game.exit();
        Assertions.assertNull(game.getState());
    }
}
