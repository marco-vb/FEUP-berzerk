package com.l01gr05.berzerk.mvc.view;

import com.googlecode.lanterna.screen.Screen;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;
import com.l01gr05.berzerk.mvc.view.game.AgentViewer;
import com.l01gr05.berzerk.mvc.view.game.EnemyViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnemyViewerTest {
    private EnemyViewer enemyViewer;
    private GUI gui;
    private Enemy enemy;

    @BeforeEach
    void setUp()  {
        this.enemy = new Enemy(new Position(10,10));
        enemyViewer = new EnemyViewer(enemy);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() {
        enemyViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawEnemy(enemyViewer.getModel());
    }
}
