package com.l01gr05.berzerk.mvc.view;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.DumbEnemy;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;
import com.l01gr05.berzerk.mvc.model.elements.Exit;
import com.l01gr05.berzerk.mvc.view.game.ArenaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaViewerTest {
    private Game game;
    private ArenaViewer arenaViewer;
    private GUI gui;
    private Agent agent;
    private Enemy enemy;
    private Exit exit;

    @BeforeEach
    void setUp()  {
        game = Mockito.mock(Game.class);
        Arena arena = new Arena(game);
        agent = new Agent(new Position(1, 1));
        enemy = new DumbEnemy(new Position(1, 1));
        exit = new Exit(new Position(9, 9));
        arena.addElement(enemy);
        arena.addElement(agent);
        arena.addElement(exit);
        arenaViewer = new ArenaViewer(arena);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void testDraw() throws IOException {
        arenaViewer.draw(gui, game);
        Mockito.verify(gui, Mockito.times(1)).drawAgent(agent);
        Mockito.verify(gui, Mockito.times(1)).drawEnemy(enemy);
        Mockito.verify(gui, Mockito.times(1)).drawExit(exit);
    }

    @Test
    public void clearAndRefresh() throws IOException {
        arenaViewer.draw(gui, game);
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }

    @Test
    public void testDrawStats() throws IOException {
        arenaViewer.drawStats(gui, game);
        Mockito.verify(gui, Mockito.times(1)).drawStats(game);
    }
}
