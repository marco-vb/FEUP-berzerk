package com.l01gr05.berzerk.mvc.view;

import com.googlecode.lanterna.screen.Screen;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.BulletViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BulletViewerTest {
    private BulletViewer bulletViewer;
    private GUI gui;
    private Bullet bullet;


    @BeforeEach
    void setUp()  {
        this.bullet= new Bullet(new Position(10,10), 'N');
        bulletViewer = new BulletViewer(bullet);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        bulletViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bulletViewer.getModel());
    }
}
