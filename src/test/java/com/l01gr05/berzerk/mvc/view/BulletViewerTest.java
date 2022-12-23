package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.model.elements.EnemyBullet;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BulletViewerTest {
    private ElementViewer bulletViewer1;
    private ElementViewer bulletViewer2;
    private GUI gui;


    @BeforeEach
    void setUp()  {
        Bullet bullet1 = new AgentBullet(new Position(10, 10), 'N');
        bulletViewer1 = bullet1.getViewer();
        Bullet bullet2 = new EnemyBullet(new Position(10, 10), 'N');
        bulletViewer2 = bullet2.getViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        bulletViewer1.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bulletViewer1.getModel());
        bulletViewer2.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bulletViewer2.getModel());
    }
}
