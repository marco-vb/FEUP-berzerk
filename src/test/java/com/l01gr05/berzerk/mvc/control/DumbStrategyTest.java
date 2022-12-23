package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.AgentController;
import com.l01gr05.berzerk.mvc.control.game.EnemyController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DumbStrategyTest {
    private EnemyController enemyController;
    private Arena arena;
    private Enemy enemy;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        Agent agent = new Agent(new Position(1, 1));
        arena = new Arena(game);
        Exit exit = new Exit(new Position(1, 3));
        enemy = new DumbEnemy(new Position(1, 2));
        arena.addElement(agent);
        arena.addElement(exit);
        arena.addElement(new Key(new Position(2, 1)));
        arena.addElement(enemy);
        enemyController = new EnemyController(arena);
    }

    @Test
    void testMove() {
        enemyController.update(game, GUI.INPUT.UP);
        Position position = enemy.getPosition();
        enemy.move(arena);
        Assertions.assertFalse(enemy.getPosition().equals(position));
    }
}
