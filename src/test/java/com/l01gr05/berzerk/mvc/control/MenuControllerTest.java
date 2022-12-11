package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.model.menu.MenuStart;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuControllerTest {
    private Game game;
    private MenuController menuController;
    private Menu menu;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
    }

    @Test
    void testMenuQuit() throws IOException {
        menu = new MenuStart();
        menuController = new MenuController(menu);
        menuController.update(game, GUI.INPUT.QUIT);
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

    @Test
    void testMenuDown() throws IOException {
        menu = new MenuStart();
        int currentOption = menu.getCurrentOption();
        menuController = new MenuController(menu);
        menuController.update(game, GUI.INPUT.DOWN);
        Assertions.assertEquals(currentOption + 1, menu.getCurrentOption());
    }

    @Test
    void testMenuUp() throws IOException {
        menu = new MenuStart();
        int currentOption = menu.getCurrentOption();
        int numberOfOptions = menu.getNumberOfOptions();
        menuController = new MenuController(menu);
        menuController.update(game, GUI.INPUT.UP);
        Assertions.assertEquals((currentOption - 1) + numberOfOptions, menu.getCurrentOption());
    }

    @Test
    void testMenuEnter() throws IOException {
        menu = Mockito.mock(Menu.class);
        menuController = new MenuController(menu);
        menuController.update(game, GUI.INPUT.ENTER);
        Mockito.verify(menu, Mockito.times(1)).executeOption(game);
    }
}
