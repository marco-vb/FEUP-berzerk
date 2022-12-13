package com.l01gr05.berzerk.states;

import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.menu.Menu;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.menu.MenuViewer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class MenuState extends State<Menu> {

    public MenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }
}
