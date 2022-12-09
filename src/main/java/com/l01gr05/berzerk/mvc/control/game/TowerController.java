package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.EnemyBullet;
import com.l01gr05.berzerk.mvc.model.elements.Tower;

import java.io.IOException;

public class TowerController extends Controller<Arena> {
    public TowerController(Arena arena) {
        super(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        double shooting_probability = 0.1;
        for (Tower tower : getModel().getTowers()) {
            if (Math.random() < shooting_probability) {
                char[] directions = {'N', 'S', 'E', 'W'};
                int random = (int) (Math.random() * directions.length);
                char direction = directions[random];
                getModel().addBullet(new EnemyBullet(tower.getPosition(), direction));
            }
        }
    }
}
