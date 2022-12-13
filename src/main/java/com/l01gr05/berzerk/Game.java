package com.l01gr05.berzerk;

import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.elements.PowerUp;
import com.l01gr05.berzerk.mvc.model.menu.MenuDeath;
import com.l01gr05.berzerk.mvc.model.menu.MenuPause;
import com.l01gr05.berzerk.mvc.model.menu.MenuSettings;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import com.l01gr05.berzerk.states.GameState;
import com.l01gr05.berzerk.states.MenuState;
import com.l01gr05.berzerk.states.State;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static final int WIDTH = 31, HEIGHT = 19, STATS_WIDTH = 15;
    public static final String BACKGROUND_COLOR = "#180030";
    private final LanternaGUI gui;
    private int level, score, lives;
    private PowerUp powerUp;
    private State state;
    private State previousState;
    private AudioInputStream inputStream;
    private Clip clip;
    boolean soundsOn = true;
    public Game() throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        this.gui = new LanternaGUI();
        this.state = new MenuState(new MenuStart());
        this.level = 1;
        this.score = 0;
        this.lives = 3;
        this.inputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/music.wav"));
        this.clip = AudioSystem.getClip();
        this.powerUp = null;
    }
    
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {

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


    private void run() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        int FPS = 10;
        long frameDuration = 1000 / FPS;

        playMusic();
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
        this.state = new GameState(new ArenaLoader(this.level, this).load());
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


    public void showDeathMenu() {
        this.state = new MenuState(new MenuDeath());
    }

    public void showPauseMenu() {
        this.state = new MenuState(new MenuPause(this));
    }


    public void exit() {
        this.state = null;
    }
    public void toggleMusic() {
        if (clip.isRunning()) {
            clip.stop();
        } else {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void toggleSound() {
        if (soundsOn) {
            soundsOn = false;
        } else {
            soundsOn = true;
        }
    }

    public boolean isSoundOn() {
        return soundsOn;
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
    public State getState () {
            return state;
        }
    public int getLevel () {
        return level;
    }


    public void resumeGame() throws IOException {
        this.state = previousState;
    }

    public void pauseGame() throws IOException {
        previousState = this.state;
    }

    public Game(LanternaGUI gui) {  // For testing purposes
        this.gui = gui;
        this.level = 1;
        this.score = 0;
        this.lives = 3;
        this.state = new MenuState(new MenuStart());
    }

    public void playMusic() {
        try {
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playShootSound() {
        if (soundsOn) {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/shot.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }

    public void playLaserSound() {
        if (soundsOn) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/laser.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public void playCannonSound() {
        if (soundsOn) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/cannon.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public void playDeathSound() {
        if (soundsOn) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/death.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

}
