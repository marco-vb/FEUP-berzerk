package com.l01gr05.berzerk.mvc.control;

public abstract class Control <Model> {
    private final Model model;

    public Control(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
