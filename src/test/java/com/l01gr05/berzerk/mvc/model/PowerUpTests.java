package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.*;
import com.l01gr05.berzerk.mvc.control.*;
import com.l01gr05.berzerk.mvc.control.menu.MenuController;
import com.l01gr05.berzerk.mvc.model.*;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.arena.ArenaLoader;
import com.l01gr05.berzerk.mvc.model.elements.Cannon;
import com.l01gr05.berzerk.mvc.model.elements.Laser;
import com.l01gr05.berzerk.mvc.model.elements.PowerUp;
import com.l01gr05.berzerk.mvc.model.elements.Shield;
import com.l01gr05.berzerk.mvc.model.menu.*;
import com.l01gr05.berzerk.mvc.view.*;
import com.l01gr05.berzerk.mvc.view.menu.MenuViewer;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class PowerUpTests {
    private PowerUp shield;
    private PowerUp cannon;
    private PowerUp laser;
    
    @BeforeEach
    void setUp() {
        shield = new Shield(new Position(1, 1));
        cannon = new Cannon(new Position(2, 2));
        laser = new Laser(new Position(3, 3));
    }

    @Test
    void testPowerUpType() {
        Assertions.assertEquals("Shield", shield.getType());
        Assertions.assertEquals("Cannon", cannon.getType());
        Assertions.assertEquals("Laser", laser.getType());
    }

    @Test
    void testPowerUpEnabled() {
        Assertions.assertFalse(shield.isEnabled());
        Assertions.assertFalse(cannon.isEnabled());
        Assertions.assertFalse(laser.isEnabled());
    }

    @Test
    void testPowerUpSwitch() {
        shield.switchPowerUp(null);
        cannon.switchPowerUp(null);
        laser.switchPowerUp(null);

        Assertions.assertTrue(shield.isEnabled());
        Assertions.assertTrue(cannon.isEnabled());
        Assertions.assertTrue(laser.isEnabled());
    }

    @Test
    public void testSetEnabled() {
        shield.setEnabled(true);
        cannon.setEnabled(true);
        laser.setEnabled(true);

        Assertions.assertTrue(shield.isEnabled());
        Assertions.assertTrue(cannon.isEnabled());
        Assertions.assertTrue(laser.isEnabled());
    }

    @Test
    public void testGetViewer() {
        Assertions.assertNotNull(shield.getViewer());
        Assertions.assertNotNull(cannon.getViewer());
        Assertions.assertNotNull(laser.getViewer());
    }
}
