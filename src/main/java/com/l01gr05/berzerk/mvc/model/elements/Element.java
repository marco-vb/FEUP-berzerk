package com.l01gr05.berzerk.mvc.model.elements;

import com.l01gr05.berzerk.mvc.model.Position;

public class Element {
    private Position position;

    public Element(Position p) {this.position = p;}

    public Position getPosition() {return position;}

    public void setPosition(Position position) {this.position = position;}
}
