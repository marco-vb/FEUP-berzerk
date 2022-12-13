package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.arena.Arena;

import java.io.IOException;

public class ArenaController extends Controller<Arena> {
    private final AgentController agentController;
    private final EnemyController enemyController;
    private final BulletController bulletController;

    private final TowerController towerController;
    private final ShieldController shieldController;
    private final LazerController lazerController;
    private final CanonController canonController;

    public ArenaController(Arena arena) {
        super(arena);

        this.agentController = new AgentController(arena);
        this.enemyController = new EnemyController(arena);
        this.bulletController = new BulletController(arena);
        this.towerController = new TowerController(arena);
        this.shieldController = new ShieldController(arena);
        this.canonController = new CanonController(arena);
        this.lazerController = new LazerController(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        if (action == GUI.INPUT.QUIT) {
            game.pauseGame();
            game.showPauseMenu();

        } else {
            agentController.update(game, action);
            enemyController.update(game, action);
            bulletController.update(game, action);
            towerController.update(game, action);
            shieldController.update(game, action);
            canonController.update(game, action);
            lazerController.update(game, action);
        }
    }
}
