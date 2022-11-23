package com.l01gr05.berzerk;

import com.l01gr05.berzerk.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(20, 20);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().run();
    }

    private void run() throws IOException {
        gui.close();
    }
}

