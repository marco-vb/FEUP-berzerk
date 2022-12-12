package com.l01gr05.berzerk.mvc.view.menu;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.view.Viewer;
import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void draw(GUI gui, Game game) throws IOException {
        gui.clear();
        gui.drawMenu(getModel());
        gui.refresh();
    }
}
