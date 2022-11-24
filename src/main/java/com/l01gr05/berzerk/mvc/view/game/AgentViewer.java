package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.view.Viewer;

public class AgentViewer extends ElementViewer {
    public AgentViewer(Agent agent) {
        super(agent);
    }

    @Override
    public void draw(GUI gui) {
        gui.drawAgent(getModel());
    }
}
