package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;

import java.io.IOException;

public class AgentController extends Controller<Arena> {
    public AgentController(Arena arena) {
        super(arena);
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        if (action == GUI.INPUT.UP) moveUp(game);
        if (action == GUI.INPUT.DOWN) moveDown(game);
        if (action == GUI.INPUT.LEFT) moveLeft(game);
        if (action == GUI.INPUT.RIGHT) moveRight(game);
        if (action == GUI.INPUT.SHOOT) shoot(game);
        if (action == GUI.INPUT.ACTIVATE) switchPowerUp(game);
        if (action == GUI.INPUT.NONE) move(getModel().getAgent().getPosition(), game);

    }

    private void moveUp(Game game) throws IOException {
        move(getModel().getAgent().getPosition().getUp(), game);
        getModel().getAgent().setDirection('N');
    }

    private void moveDown(Game game) throws IOException {
        move(getModel().getAgent().getPosition().getDown(), game);
        getModel().getAgent().setDirection('S');
    }

    private void moveLeft(Game game) throws IOException {
        move(getModel().getAgent().getPosition().getLeft(), game);
        getModel().getAgent().setDirection('W');
    }

    private void moveRight(Game game) throws IOException {
        move(getModel().getAgent().getPosition().getRight(), game);
        getModel().getAgent().setDirection('E');
    }

    private void shoot(Game game) {
        Agent agent = getModel().getAgent();
        if (agent.getPowerUp() instanceof Canon && agent.getPowerUp().isEnabled()) {
            AgentBullet northBullet = new AgentBullet(agent.getPosition(), 'N');
            AgentBullet southBullet = new AgentBullet(agent.getPosition(), 'S');
            AgentBullet eastBullet = new AgentBullet(agent.getPosition(), 'E');
            AgentBullet westBullet = new AgentBullet(agent.getPosition(), 'W');
            getModel().addBullet(northBullet);
            getModel().addBullet(southBullet);
            getModel().addBullet(eastBullet);
            getModel().addBullet(westBullet);
            agent.setPowerUp(null);
            game.setPowerUp(null);
            game.setIsPowerUpActive(false);
        } else if (agent.getPowerUp() instanceof Lazer && agent.getPowerUp().isEnabled()) {
            char direction = agent.getDirection();
            Position position = agent.getPosition();
            while (!getModel().isWall(position)){
                getModel().addBullet(new AgentBullet(position, direction));
                if (direction == 'N') position = position.getUp();
                if (direction == 'S') position = position.getDown();
                if (direction == 'E') position = position.getRight();
                if (direction == 'W') position = position.getLeft();
            }
            agent.setPowerUp(null);
            game.setPowerUp(null);
            game.setIsPowerUpActive(false);
        } else {
            AgentBullet bullet = new AgentBullet(getModel().getAgent().getPosition(), getModel().getAgent().getDirection());
            getModel().addBullet(bullet);
        }

    }

    private void switchPowerUp(Game game) {
        if (getModel().getAgent().getPowerUp() != null) {
            getModel().getAgent().getPowerUp().switchPowerUp(getModel().getAgent());
            game.setPowerUp(getModel().getAgent().getPowerUp());
            game.setIsPowerUpActive(getModel().getAgent().getPowerUp().isEnabled());
        }
    }
    private void move(Position position, Game game) throws IOException {
        Arena arena = getModel();
        Agent agent = getModel().getAgent();
        if (arena.getKey() == null) arena.setOpen();
        if (arena.isWall(position)) {
            game.decreaseLives();
            if (game.isGameOver()) {
                game.showStartMenu();
                agent.setPowerUp(null);
                game.setPowerUp(agent.getPowerUp());
                game.setIsPowerUpActive(false);
            }
            agent.setPosition(agent.getInitialPosition());
        } else if (arena.isEnemy(position)){
            if (agent.getPowerUp() != null && agent.getPowerUp() instanceof Shield && agent.getPowerUp().isEnabled())
            {
                agent.setPowerUp(null);
                game.setPowerUp(agent.getPowerUp());
                game.setIsPowerUpActive(false);
                return;
            }
            game.decreaseLives();
            if (game.isGameOver()) {
                game.showStartMenu();
                agent.setPowerUp(null);
                game.setPowerUp(agent.getPowerUp());
                game.setIsPowerUpActive(false);
            }
            agent.setPosition(agent.getInitialPosition());
        } else if (arena.isExit(position)) {
            game.nextLevel();
            getModel().getAgent().setPowerUp(game.getPowerUp());
            if (arena.getAgent().getPowerUp() != null )arena.getAgent().getPowerUp().setEnabled(game.isPowerUpActive());
        } else if (arena.isKey(position)) {
            arena.removeKey();
            arena.setOpen();
            agent.setPosition(position);
        } else {
            agent.setPosition(position);
        }
    }
}
