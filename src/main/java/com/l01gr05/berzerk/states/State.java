package com.l01gr05.berzerk.states;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.view.Viewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class State<Model> {
    private final Model model;
    private final Controller<Model> controller;
    private final Viewer<Model> viewer;

    public State(Model model) {
        this.model = model;
        this.controller = getController();
        this.viewer = getViewer();
    }

    protected abstract Controller<Model> getController();
    protected abstract Viewer<Model> getViewer();

    public Model getModel() {
        return model;
    }

    public void update(Game game, GUI gui) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GUI.INPUT action = gui.getInput();
        controller.update(game, action);
        viewer.draw(gui, game);
    }

    public State(Model model, Controller<Model> controller, Viewer<Model> viewer) { // For testing purposes
        this.model = model;
        this.controller = controller;
        this.viewer = viewer;
    }
}
