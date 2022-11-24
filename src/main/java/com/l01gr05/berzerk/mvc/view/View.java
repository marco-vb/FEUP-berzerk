package com.l01gr05.berzerk.mvc.view;

public abstract class View <Model> {
    private final Model model;

    public View(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
