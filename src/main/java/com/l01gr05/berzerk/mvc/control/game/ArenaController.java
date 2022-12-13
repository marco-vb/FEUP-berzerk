package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.arena.Arena;

import java.io.IOException;

public class ArenaController extends Controller<Arena> {
    private AgentController agentController;
    private final EnemyController enemyController;
    private final BulletController bulletController;
    private final TowerController towerController;

    public ArenaController(Arena arena) {
        super(arena);
        this.agentController = new AgentController(arena);
        this.enemyController = new EnemyController(arena);
        this.bulletController = new BulletController(arena);
        this.towerController = new TowerController(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        if (action == GUI.INPUT.QUIT) {
            game.showStartMenu();
        } else {
            agentController.update(game, action);
            enemyController.update(game, action);
            bulletController.update(game, action);
            towerController.update(game, action);
        }
    }
}
