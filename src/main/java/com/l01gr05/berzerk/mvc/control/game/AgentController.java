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
        if (action == GUI.INPUT.UP) moveUp();
        if (action == GUI.INPUT.DOWN) moveDown();
        if (action == GUI.INPUT.LEFT) moveLeft();
        if (action == GUI.INPUT.RIGHT) moveRight();
    }

    private void moveUp() {
        move(getModel().getAgent().getPosition().getUp());
    }

    private void moveDown() {
        move(getModel().getAgent().getPosition().getDown());
    }

    private void moveLeft() {
        move(getModel().getAgent().getPosition().getLeft());
    }

    private void moveRight() {
        move(getModel().getAgent().getPosition().getRight());
    }

    private void move(Position position) {
        Agent agent = getModel().getAgent();
        if (getModel().isEmpty(position)) {
            agent.setPosition(position);
        }
    }
}
