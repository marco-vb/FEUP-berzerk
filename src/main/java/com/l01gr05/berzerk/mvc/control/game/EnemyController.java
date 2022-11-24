package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;

public class EnemyController extends Controller<Arena> {
    public EnemyController(Arena arena) {
        super(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) {
        for (Enemy enemy : getModel().getEnemies()) {
            move(enemy, enemy.getPosition().getRandom());
        }
    }

    private void move(Enemy enemy, Position position) {
        if (getModel().isEmpty(position)) {
            enemy.setPosition(position);
        }
    }

}
