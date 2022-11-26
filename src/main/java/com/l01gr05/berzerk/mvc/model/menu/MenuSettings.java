package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.util.List;

public class MenuSettings extends Menu {
    public MenuSettings() {
        super("Settings", List.of("Music", "Sound", "Back"));
    }

    @Override
    public void executeOption(Game game) {
        switch (getCurrentOption()) {
            case 0: game.toggleMusic(); break;
            case 1: game.toggleSound(); break;
            case 2: game.showStartMenu(); break;
        }
    }
}
