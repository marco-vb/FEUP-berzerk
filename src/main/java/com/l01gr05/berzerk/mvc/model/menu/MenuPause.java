package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.states.State;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuPause extends Menu {
    public MenuPause(Game game) {
        super("GAME PAUSED", Arrays.asList("CONTINUE", "MAIN MENU", "EXIT"));
    }

    @Override
    public void executeOption(Game game) throws IOException {
        switch (getCurrentOption()) {
            case 0: game.resumeGame(); break;
            case 1: game.showStartMenu(); break;
            case 2: game.exit(); break;
        }
    }
}
