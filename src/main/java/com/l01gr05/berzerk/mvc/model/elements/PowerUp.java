package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;

public abstract class PowerUp extends Element {
    private final String type;
    public PowerUp(Position p, String type) {
        super(p);
        this.type = type;
    }
    public String getType() {
        return type;
    }


}