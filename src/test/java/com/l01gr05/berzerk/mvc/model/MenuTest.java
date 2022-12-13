package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.menu.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class MenuTest {
    private Menu menu;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
    }

    @Test
    void testStartMenuExecute() throws IOException {
        menu = new MenuStart();
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).startGame();
    }

    @Test
    void testStartMenuExecute2() throws IOException {
        menu = new MenuStart();
        menu.setCurrentOption(1);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).showSettings();
    }

    @Test
    void testStartMenuExecute3() throws IOException {
        menu = new MenuStart();
        menu.setCurrentOption(2);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).exit();
    }

    @Test
    void testSettingsMenuExecute() throws IOException {
        menu = new MenuSettings();
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).toggleMusic();
    }

    @Test
    void testSettingsMenuExecute2() throws IOException {
        menu = new MenuSettings();
        menu.setCurrentOption(1);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).toggleSound();
    }

    @Test
    void testSettingsMenuExecute3() throws IOException {
        menu = new MenuSettings();
        menu.setCurrentOption(2);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).showStartMenu();
    }

    @Test
    void testPauseMenuExecute() throws IOException {
        menu = new MenuPause(game);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).resumeGame();
    }

    @Test
    void testPauseMenuExecute2() throws IOException {
        menu = new MenuPause(game);
        menu.setCurrentOption(1);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).showStartMenu();
    }

    @Test
    void testPauseMenuExecute3() throws IOException {
        menu = new MenuPause(game);
        menu.setCurrentOption(2);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).exit();
    }

    @Test
    void testDeathMenuExecute() throws IOException {
        menu = new MenuDeath();
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).startGame();
    }

    @Test
    void testDeathMenuExecute2() throws IOException {
        menu = new MenuDeath();
        menu.setCurrentOption(1);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).showStartMenu();
    }

    @Test
    void testDeathMenuExecute3() throws IOException {
        menu = new MenuDeath();
        menu.setCurrentOption(2);
        menu.executeOption(game);
        Mockito.verify(game, Mockito.times(1)).exit();
    }

    @Test
    void testMenuStart() {
        menu = new MenuStart();
        boolean title = menu.getTitle().equals("BERZERK");
        boolean options = menu.getOptions().equals(List.of("START", "SETTINGS", "EXIT"));
        Assertions.assertTrue(title && options);
    }

    @Test
    void testMenuSettings() {
        menu = new MenuSettings();
        boolean title = menu.getTitle().equals("SETTINGS");
        boolean options = menu.getOptions().equals(List.of("MUSIC", "SOUND", "BACK"));
        Assertions.assertTrue(title && options);
    }
}
