package com.l01gr05.berzerk.states;

import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.view.Viewer;

public class MenuState extends State<Menu> {

    public MenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Controller getController() {
        return new MenuController(getModel());
    }

    @Override
    protected Viewer getViewer() {
        return null;
    }
}
