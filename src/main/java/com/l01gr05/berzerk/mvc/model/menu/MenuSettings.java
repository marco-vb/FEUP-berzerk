package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.Game;

import java.util.Arrays;
import java.util.List;

public class MenuSettings extends Menu {
    public MenuSettings() {
        super("SETTINGS", Arrays.asList("MUSIC", "SOUND", "BACK"));
    }

    public MenuSettings(Game game) {
        super("SETTINGS", List.of(String.format("MUSIC %s", game.isMusicOn() ? "ON" : "OFF") ,String.format("SOUND %s", game.isSoundOn() ? "ON" : "OFF"), "BACK"));
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
