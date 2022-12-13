package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.util.Arrays;
import java.util.List;

public class MenuSettings extends Menu {
    public MenuSettings() {
        super("SETTINGS", Arrays.asList("MUSIC", "SOUND", "BACK"));
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
