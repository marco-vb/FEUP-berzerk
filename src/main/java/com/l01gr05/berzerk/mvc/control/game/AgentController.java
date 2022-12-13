package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.model.elements.EnemyBullet;

import java.io.IOException;
import java.util.List;

public class AgentController extends Controller<Arena> {
    private AgentState state;
    public AgentController(Arena arena) {
        super(arena);
        this.state = new AgentNormal();
    }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        if (action == GUI.INPUT.UP) moveUp(game);
        if (action == GUI.INPUT.DOWN) moveDown(game);
        if (action == GUI.INPUT.LEFT) moveLeft(game);
        if (action == GUI.INPUT.RIGHT) moveRight(game);
        if (action == GUI.INPUT.SHOOT) shoot(game);
        if (action == GUI.INPUT.ACTIVATE) switchPowerUp();
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
    public void shoot(Game game) {
        state.shoot(game, getModel());
    }

    public void move(Position position, Game game) throws IOException {
        Arena arena = getModel();
        Agent agent = arena.getAgent();
        if (arena.getKey() == null) arena.setOpen();
        if (!(state instanceof AgentShield)) {
            List<Bullet> bullets = arena.getBullets();
            for (Bullet bullet : bullets)
                if (bullet instanceof EnemyBullet && bullet.getPosition().equals(position)) {
                    gotHit(game, agent); return;
                }
        }
        if (arena.isWall(position) || arena.isEnemy(position)) {
            gotHit(game, agent); return;
        }
        if (arena.isExit(position)) {
            game.nextLevel();
            arena.getAgent().setPowerUp(game.getPowerUp());
            return;
        }
        if (arena.isKey(position)) {
            arena.removeKey();
            arena.setOpen();
        }
       if (arena.isPowerUp(position)) {
            agent.setPowerUp(arena.getPowerUp(position));
            game.setPowerUp(agent.getPowerUp());
            arena.removePowerUp(position);
        }
       agent.setPosition(position);
    }

    private void gotHit(Game game, Agent agent) {
        game.decreaseLives();
        if (game.isGameOver()) game.showStartMenu();
        agent.setPosition(agent.getInitialPosition());
    }

    private void switchPowerUp() {
        switch (getModel().getAgent().getPowerUp().getType()) {
            case "Laser":
                state = new AgentLaser(); break;
            case "Shield":
                state = new AgentShield(); break;
            case "Cannon":
                state = new AgentCannon(); break;
            default:
                state = new AgentNormal(); break;
        }
    }
}
