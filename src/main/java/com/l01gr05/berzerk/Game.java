package com.l01gr05.berzerk;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.menu.MenuSettings;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.states.GameState;
import com.l01gr05.berzerk.states.MenuState;
import com.l01gr05.berzerk.states.State;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(20, 20);
        this.state = new MenuState(new MenuStart());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.run();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void run() throws IOException {
        int FPS = 10;
        long frameDuration = 1000 / FPS;

        while (state != null) {
            long start = System.currentTimeMillis();
            state.update(this, gui);
            long end = System.currentTimeMillis();
            long duration = end - start;
            if (duration < frameDuration) {
                try {
                    Thread.sleep(frameDuration - duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        gui.close();
    }

    public void startGame() throws IOException {
        this.state = new GameState(new ArenaLoader(1).load());
    }

    public void showStartMenu() {
        this.state = new MenuState(new MenuStart());
    }

    public void showSettings() {
        this.state = new MenuState(new MenuSettings());
    }

    public void exit() {
        this.state = null;
    }

    public void toggleMusic() {
        // TODO
    }

    public void toggleSound() {
        // TODO
    }
}

