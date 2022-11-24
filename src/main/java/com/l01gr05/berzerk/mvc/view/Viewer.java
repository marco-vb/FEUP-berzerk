package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.gui.GUI;

import java.io.IOException;

public abstract class Viewer<Model> {
    private final Model model;

    public Viewer(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void draw(GUI gui) throws IOException;
}
