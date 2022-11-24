package com.l01gr05.berzerk.gui;

import com.l01gr05.berzerk.mvc.model.elements.Element;

import java.io.IOException;

public interface GUI {
    void clear() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;

    INPUT getInput() throws IOException;

    abstract void drawWall(Element model);

    abstract void drawAgent(Element model);

    abstract void drawEnemy(Element model);

    //abstract void drawBullet(Element model);

    abstract void draw(int x, int y, char c);

    enum INPUT {UP, DOWN, LEFT, RIGHT, NONE, QUIT, ENTER, SHOOT};
}
