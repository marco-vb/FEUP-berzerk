package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Cannon;
import com.l01gr05.berzerk.mvc.model.elements.Laser;
import com.l01gr05.berzerk.mvc.model.elements.Shield;
import com.l01gr05.berzerk.mvc.view.game.CannonViewer;
import com.l01gr05.berzerk.mvc.view.game.LaserViewer;
import com.l01gr05.berzerk.mvc.view.game.ShieldViewer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class PowerUpViewersTest {
    private GUI gui;

    @BeforeEach
    void setUp()  {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDrawCannon() {
        Cannon cannon = Mockito.mock(Cannon.class);
        CannonViewer cannonViewer = new CannonViewer(cannon);
        Mockito.when(cannon.getViewer()).thenReturn(cannonViewer);
        cannonViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawCannon(Mockito.any(Cannon.class));
    }

    @Test
    public void testDrawShield() {
        ShieldViewer shieldViewer = new ShieldViewer(new Shield(new Position(10,10)));
        shieldViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawShield(shieldViewer.getModel());
    }

    @Test
    public void testDrawLaser() {
        LaserViewer laserViewer = new LaserViewer(new Laser(new Position(10,10)));
        laserViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawLaser(laserViewer.getModel());
    }
}
