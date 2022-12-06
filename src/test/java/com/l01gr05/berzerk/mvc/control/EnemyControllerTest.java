package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
import com.l01gr05.berzerk.mvc.control.game.EnemyController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;
import com.l01gr05.berzerk.mvc.model.elements.Exit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EnemyControllerTest {
    private Position startingPos;
    private EnemyController enemyController;
    private Enemy enemy;
    private Agent agent;
    private Arena arena;
    private Exit exit;
    private Game game;
    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        agent = new Agent(new Position(1, 1));
        enemy = new Enemy(new Position(1, 1));
        arena = new Arena(10, 10, game);
        exit = new Exit(new Position(9, 9));
        arena.addElement(enemy);
        arena.addElement(agent);
        arena.addElement(exit);
        enemyController = new EnemyController(arena);
    }

    @Test
    void testMoveEnemy() {
        startingPos = enemy.getPosition();
        enemyController.update(game, GUI.INPUT.UP);
        assertNotEquals(startingPos, enemy.getPosition());
    }
}
