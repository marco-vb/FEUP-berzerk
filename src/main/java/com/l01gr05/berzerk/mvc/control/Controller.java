package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;

import java.io.FileNotFoundException;

public abstract class Controller<Model> {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void update(Game game, GUI.INPUT action) throws FileNotFoundException;
}
