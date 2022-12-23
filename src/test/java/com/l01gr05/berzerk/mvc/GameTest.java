package com.l01gr05.berzerk.mvc;


import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.mvc.model.elements.PowerUp;
import com.l01gr05.berzerk.states.State;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

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

    @Test
    public void testDeathMenu() throws IOException {
        game.showDeathMenu();
        Assertions.assertNotNull(game.getState());
    }

    @Test
    public void testPauseMenu() throws IOException {
        game.showPauseMenu();
        Assertions.assertNotNull(game.getState());
    }

    @Test
    public void testSoundOn() throws IOException {
        Assertions.assertTrue(game.isSoundOn());
    }

    @Test
    public void testSetPowerUp() {
        PowerUp powerUp = Mockito.mock(PowerUp.class);
        game.setPowerUp(powerUp);
        Assertions.assertEquals(powerUp, game.getPowerUp());
    }

    @Test
    public void testPowerUpActive() {
        PowerUp powerUp = Mockito.mock(PowerUp.class);
        Mockito.when(powerUp.isEnabled()).thenReturn(true);
        game.setPowerUp(powerUp);
        Assertions.assertTrue(game.isPowerUpActive());
    }

    @Test
    public void testResumeGame() throws IOException {
        game.resumeGame();
        Assertions.assertNull(game.getState());
    }

    @Test
    public void testPauseGame() throws IOException {
        game.pauseGame();
        Assertions.assertNotNull(game.getState());
    }

    @Test
    public void testSounds() {
        Clip clip = Mockito.mock(Clip.class);
        game = new Game(gui, clip);
        Mockito.when(clip.isRunning()).thenReturn(true, false);
        game.toggleMusic();
        Mockito.verify(clip, Mockito.times(1)).stop();
        game.toggleMusic();
        Mockito.verify(clip, Mockito.times(1)).start();
        Mockito.verify(clip, Mockito.times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    public void testToggleSound() {
        Assertions.assertTrue(game.isSoundOn());
        game.toggleSound();
        Assertions.assertFalse(game.isSoundOn());
    }

    @Test
    public void testPlayMusic() throws LineUnavailableException, IOException {
        Clip clip = Mockito.mock(Clip.class);
        game = new Game(gui, clip);
        game.playMusic();
        Mockito.verify(clip, Mockito.times(1)).open(Mockito.any());
        Mockito.verify(clip, Mockito.times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }
}
