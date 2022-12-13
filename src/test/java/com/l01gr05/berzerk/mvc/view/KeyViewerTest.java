package com.l01gr05.berzerk.mvc.view;

import com.googlecode.lanterna.screen.Screen;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Key;
import com.l01gr05.berzerk.mvc.model.elements.Tower;
import com.l01gr05.berzerk.mvc.view.game.KeyViewer;
import com.l01gr05.berzerk.mvc.view.game.TowerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KeyViewerTest {
    private KeyViewer keyViewer;
    private GUI gui;
    private Key key;

    @BeforeEach
    void setUp()  {
        this.key = new Key(new Position(10,10));
        keyViewer = key.getViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        keyViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawKey(keyViewer.getModel());
    }
}
