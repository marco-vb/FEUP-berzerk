package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.arena.Arena;

public class ArenaController extends Controller<Arena> {
    private final AgentController agentController;
    private final EnemyController enemyController;

    public ArenaController(Arena arena) {
        super(arena);

        this.agentController = new AgentController(arena);
        this.enemyController = new EnemyController(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) {
        if (action == GUI.INPUT.QUIT) {
            game.setState(null);
        } else {
            agentController.update(game, action);
            enemyController.update(game, action);
        }
    }
}
