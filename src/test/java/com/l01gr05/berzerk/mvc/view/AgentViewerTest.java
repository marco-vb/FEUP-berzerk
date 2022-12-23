package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AgentViewerTest {
    private AgentViewer agentViewer;
    private GUI gui;

    @BeforeEach
    void setUp()  {
        Agent agent = new Agent(new Position(10, 10));
        agentViewer = new AgentViewer(agent);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        agentViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawAgent(agentViewer.getModel());
    }
}

