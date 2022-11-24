package com.l01gr05.berzerk.mvc.control.menu;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.menu.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        selectOption(game, action);
    }

    private void selectOption(int option) {
        getModel().setCurrentOption(option);
    }

    private void selectOption(Game game, GUI.INPUT action) throws IOException {
        int currentOption = getModel().getCurrentOption();
        int numberOfOptions = getModel().getNumberOfOptions();

        if (action == GUI.INPUT.DOWN) {
            selectOption((currentOption + 1) % numberOfOptions);
        } else if (action == GUI.INPUT.UP) {
            selectOption((currentOption - 1) % numberOfOptions);
        }
        else if (action == GUI.INPUT.ENTER) {
            getModel().executeOption(game);
        }
        else if (action == GUI.INPUT.QUIT) {
            game.setState(null);
        }
    }
}
