package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.AgentBullet;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;

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
        if (action == GUI.INPUT.SHOOT) shoot();
        if (action == GUI.INPUT.NONE) move(getModel().getAgent().getPosition(), game);
    }

    private void moveUp(Game game) {
        move(getModel().getAgent().getPosition().getUp(), game);
        getModel().getAgent().setDirection('N');
    }

    private void moveDown(Game game) {
        move(getModel().getAgent().getPosition().getDown(), game);
        getModel().getAgent().setDirection('S');
    }

    private void moveLeft(Game game) {
        move(getModel().getAgent().getPosition().getLeft(), game);
        getModel().getAgent().setDirection('W');
    }

    private void moveRight(Game game) {
        move(getModel().getAgent().getPosition().getRight(), game);
        getModel().getAgent().setDirection('E');
    }

    private void shoot() {
        AgentBullet bullet = new AgentBullet(getModel().getAgent().getPosition(), getModel().getAgent().getDirection());
        getModel().addBullet(bullet);
    }

    private void move(Position position, Game game) {
        Agent agent = getModel().getAgent();
        if (getModel().isWall(position) || getModel().isEnemy(position)) {
            agent.setLives(agent.getLives() - 1);
            if (agent.isDead()) game.showStartMenu();
            agent.setPosition(agent.getInitialPosition());
        } else if (getModel().isExit(position)) {
            agent.setScore(agent.getScore() + 100);
            game.showStartMenu();
        } else {
            agent.setPosition(position);
        }
    }
}
