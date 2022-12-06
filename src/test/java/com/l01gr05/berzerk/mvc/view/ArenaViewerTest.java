package com.l01gr05.berzerk.mvc.view;

import com.googlecode.lanterna.screen.Screen;
import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.EnemyController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;
import com.l01gr05.berzerk.mvc.model.elements.Exit;
import com.l01gr05.berzerk.mvc.view.game.ArenaViewer;
import com.l01gr05.berzerk.mvc.view.game.ExitViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaViewerTest {
    private ArenaViewer arenaViewer;
    private GUI gui;
    private Arena arena;
    private Agent agent;
    private Enemy enemy;
    private Exit exit;

    @BeforeEach
    void setUp()  {
        Game game = Mockito.mock(Game.class);
        this.arena = new Arena(10,10, game);
        agent = new Agent(new Position(1, 1));
        enemy = new Enemy(new Position(1, 1));
        exit = new Exit(new Position(9, 9));
        arena.addElement(enemy);
        arena.addElement(agent);
        arena.addElement(exit);
        arenaViewer = new ArenaViewer(arena);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void clearAndRefresh() throws IOException {
        arenaViewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
