package com.l01gr05.berzerk.states;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.view.Viewer;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.controller = getController();
        this.viewer = getViewer();
    }

    protected abstract Controller<T> getController();
    protected abstract Viewer<T> getViewer();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui) {

    }
}
