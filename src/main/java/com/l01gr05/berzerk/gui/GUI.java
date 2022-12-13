package com.l01gr05.berzerk.gui;

import com.googlecode.lanterna.TextColor;
import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Element;
import com.l01gr05.berzerk.mvc.model.menu.Menu;

import java.io.IOException;

public interface GUI {
    void clear() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;

    INPUT getInput() throws IOException;

    void drawAgent(Element model);

    void drawExit(Element model);

    void drawWall(Element model);

    void drawEnemy(Element model);

    void drawBullet(Element model);

    void drawKey(Element model);

    void drawTower(Element model);

    void drawShield(Element model);

    void drawCanon(Element model);

    void drawLaser(Element model);

    void draw(int x, int y, char c, TextColor color);

    void drawMenu(Menu menu);

    void drawStats(Game game);

    enum INPUT {UP, DOWN, LEFT, RIGHT, NONE, QUIT, ENTER, ACTIVATE, SHOOT};
}
