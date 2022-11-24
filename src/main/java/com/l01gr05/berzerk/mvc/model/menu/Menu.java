package com.l01gr05.berzerk.mvc.model.menu;

import com.l01gr05.berzerk.mvc.view.Viewer;

public abstract class Menu {
    private Viewer<Menu> viewer;

    public Menu() {
    }

    public Viewer<Menu> getViewer() {
        return viewer;
    }
}
