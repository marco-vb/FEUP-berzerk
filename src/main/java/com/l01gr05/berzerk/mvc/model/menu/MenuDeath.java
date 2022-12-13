package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.io.IOException;
import java.util.List;

public class MenuDeath extends Menu{
    public MenuDeath() {
        super("YOU LOST", List.of("RETRY", "MAIN MENU", "EXIT"));
    }

    @Override
    public void executeOption(Game game) throws IOException {
        switch (getCurrentOption()) {
            case 0: game.startGame(); break;
            case 1: game.showStartMenu(); break;
            case 2: game.exit(); break;
        }
    }
}
