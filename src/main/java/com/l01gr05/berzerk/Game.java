package com.l01gr05.berzerk;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.gui.LanternaGUI;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.states.GameState;
import com.l01gr05.berzerk.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(20, 20);
        this.state = new GameState(new ArenaLoader(1).load());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().run();
    }

    private void setState (State state) {
        this.state = state;
    }

    private void run() throws IOException {
        while (state != null) {
            // Control reads keys and takes care of movement
            // View updates screen
            state.update(this, gui);

        }
        gui.close();
    }
}

