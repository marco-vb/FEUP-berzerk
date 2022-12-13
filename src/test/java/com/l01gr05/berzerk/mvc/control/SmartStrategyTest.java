package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.game.EnemyController;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class SmartStrategyTest {
    private EnemyController enemyController;
    private Agent agent;
    private Arena arena;
    private Exit exit;
    private Enemy enemy;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        agent = new Agent(new Position(1, 1));
        arena = new Arena(game);
        exit = new Exit(new Position(1, 3));
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
        Position upPos = enemy.getPosition().getUp();
        Position downPos = enemy.getPosition().getDown();
        Position leftPos = enemy.getPosition().getLeft();
        Position rightPos = enemy.getPosition().getRight();
        Position upRightPos = enemy.getPosition().getRight().getUp();
        Position upLeftPos = enemy.getPosition().getLeft().getUp();
        Position downRightPos = enemy.getPosition().getRight().getDown();
        Position downLeftPos = enemy.getPosition().getLeft().getDown();
        enemy.move(arena);
        boolean movedCorrectly = false;
        if (enemy.getPosition().equals(upPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(downPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(leftPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(rightPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(upRightPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(upLeftPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(downRightPos)) {
            movedCorrectly = true;
        } else if (enemy.getPosition().equals(downLeftPos)) {
            movedCorrectly = true;
        }
        assertTrue(movedCorrectly);
    }
}
