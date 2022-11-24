package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;

public class AgentController extends Controller<Arena> {
    public AgentController(Arena arena) {
        super(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) {
        if (action == GUI.INPUT.UP) moveUp(game);
        if (action == GUI.INPUT.DOWN) moveDown(game);
        if (action == GUI.INPUT.LEFT) moveLeft(game);
        if (action == GUI.INPUT.RIGHT) moveRight(game);
    }

    private void moveUp(Game game) {
        move(getModel().getAgent().getPosition().getUp(), game);
    }

    private void moveDown(Game game) {
        move(getModel().getAgent().getPosition().getDown(), game);
    }

    private void moveLeft(Game game) {
        move(getModel().getAgent().getPosition().getLeft(), game);
    }

    private void moveRight(Game game) {
        move(getModel().getAgent().getPosition().getRight(), game);
    }

    private void move(Position position, Game game) {
        Agent agent = getModel().getAgent();
        if (getModel().isWall(position) || getModel().isEnemy(position) || getModel().isExit(position)) {
            game.setState(null);
        }

        else {
            agent.setPosition(position);
        }

    }
}
