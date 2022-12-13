package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MenuStart extends Menu {
    public MenuStart() {
        super("BERZERK", List.of("START", "SETTINGS", "EXIT"));
    }

    @Override
    public void executeOption(Game game) throws IOException {
        switch (getCurrentOption()) {
            case 0: game.startGame(); break;
            case 1: game.showSettings(); break;
            case 2: game.exit(); break;
        }
    }
}
