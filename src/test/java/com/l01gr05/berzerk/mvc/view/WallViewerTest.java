package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Wall;
import com.l01gr05.berzerk.mvc.view.game.ElementViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WallViewerTest {
    private ElementViewer wallViewer;
    private GUI gui;

    @BeforeEach
    void setUp()  {
        Wall wall = new Wall(new Position(10, 10));
        wallViewer = wall.getViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        wallViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(wallViewer.getModel());
    }
}
