package com.l01gr05.berzerk.gui;

import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Element;
import com.l01gr05.berzerk.mvc.model.menu.Menu;

import java.io.IOException;

public interface GUI {
    void clear() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;

    INPUT getInput() throws IOException;

    abstract void drawAgent(Element model);

    abstract void drawExit(Element model);

    abstract void drawWall(Element model);

    abstract void drawEnemy(Element model);

    abstract void drawBullet(Element model);

    abstract void draw(int x, int y, char c);

    void drawMenu(Menu menu);

    void drawStats(Arena model);

    enum INPUT {UP, DOWN, LEFT, RIGHT, NONE, QUIT, ENTER, SHOOT};
}
