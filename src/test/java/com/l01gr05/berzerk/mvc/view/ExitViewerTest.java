package com.l01gr05.berzerk.mvc.view;

import com.googlecode.lanterna.screen.Screen;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Exit;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.ExitViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ExitViewerTest {
    private ExitViewer exitViewer;
    private GUI gui;
    private Exit exit;
    private Screen screen;

    @BeforeEach
    void setUp()  {
        this.exit = new Exit(new Position(10,10));
        exitViewer = new ExitViewer(exit);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        exitViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawExit(exitViewer.getModel());
    }
}
