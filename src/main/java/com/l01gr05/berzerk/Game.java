package com.l01gr05.berzerk;

import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.elements.PowerUp;
import com.l01gr05.berzerk.mvc.model.menu.MenuSettings;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.states.GameState;
import com.l01gr05.berzerk.states.MenuState;
import com.l01gr05.berzerk.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 19;
    private int level;
    private int score;
    private int lives;
    private PowerUp powerUp;
    private boolean isPowerUpActive;
    private final LanternaGUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(WIDTH, HEIGHT, this);
        this.state = new MenuState(new MenuStart());
        this.level = 1;
        this.score = 0;
        this.lives = 3;
        this.powerUp = null;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.run();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void decreaseLives() {
        this.lives--;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return lives == 0;
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
        this.score = 0;
        this.lives = 3;
        this.level = 1;
        this.powerUp = null;
        this.state = new GameState(new ArenaLoader(1, this).load());
    }

    public void nextLevel() throws IOException {
        this.level++;
        this.score += 100;
        this.state = new GameState(new ArenaLoader(this.level, this).load());
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

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public boolean isPowerUpActive() {
        return (powerUp == null) ? false : powerUp.isEnabled();
    }

    public void setIsPowerUpActive(boolean state) {
        isPowerUpActive = state;
    }
}

