package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.io.FileNotFoundException;
import java.util.List;

public class MenuStart extends Menu {
    public MenuStart() {
        super("Berzerk", List.of("Start", "Settings", "Exit"));
    }

    @Override
    public void executeOption(Game game) throws FileNotFoundException {
        switch (getCurrentOption()) {
            case 0 -> game.startGame();
            case 1 -> game.showSettings();
            case 2 -> game.exit();
        }
    }
}
