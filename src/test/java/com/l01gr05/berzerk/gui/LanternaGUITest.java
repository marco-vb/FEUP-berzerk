package com.l01gr05.berzerk.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        this.screen = Mockito.mock(Screen.class);
        this.tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        this.gui = new LanternaGUI(screen);
    }

    @Test
    void drawAgent() {
        gui.drawAgent(new Agent(new Position(1,1)));

        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "A");
    }

    @Test
    void drawExit() {
        gui.drawExit(new Exit(new Position(1,1)));

        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, " ");
    }

    @Test
    void drawWall() {
        gui.drawWall(new Wall(new Position(1,1)));

        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "#");
    }

    @Test
    void drawEnemy() {
        gui.drawEnemy(new Enemy(new Position(1,1)));

        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "E");
    }

    //Could be better?
    @Test
    void drawBullet() {
        gui.drawBullet(new Bullet(new Position(1,1), 'N'));

        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, ".");
    }

    @Test
    void draw() {
        gui.draw(1,1, 'b');
        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "b");
    }
}
