package com.l01gr05.berzerk.gui;

import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Element;

import java.io.IOException;

public interface GUI {
    void clear() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;

    INPUT getInput() throws IOException;

    void drawWall(Element model);

    void drawAgent(Agent model);

    void drawEnemy(Element model);

    enum INPUT {UP, DOWN, LEFT, RIGHT, NONE, QUIT, ENTER, SHOOT};
}
