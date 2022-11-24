package com.l01gr05.berzerk.mvc.control.menu;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.states.GameState;
import com.l01gr05.berzerk.states.MenuState;

import java.io.FileNotFoundException;

public class MenuController extends Controller<Menu> {
    private final Menu menu;
    private final int numOptions;
    private int selectedOption;
    public MenuController(Menu menu) {
        super(menu);
        this.menu = menu;
        this.numOptions = 3;
        this.selectedOption = 0;
    }

    private void selectOption(int option) {
        selectedOption = option;
    }

    private void selectOption(Game game, GUI.INPUT action) throws FileNotFoundException {
        if (action == GUI.INPUT.DOWN) {
            selectOption((selectedOption + 1) % numOptions);
        } else if (action == GUI.INPUT.UP) {
            selectOption((selectedOption - 1) % numOptions);
        }
        else if (action == GUI.INPUT.ENTER) {
            executeOption(game, selectedOption);
        }
        else if (action == GUI.INPUT.QUIT) {
            game.setState(null);
        }
    }

    private void executeOption(Game game, int selectedOption) throws FileNotFoundException {
        switch (selectedOption) {
            case 0 -> game.setState(new GameState(new ArenaLoader(1).load()));
            case 1 -> game.setState(new MenuState(menu));
            default -> game.setState(null);
        }
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws FileNotFoundException {
        selectOption(game, action);
    }
}
