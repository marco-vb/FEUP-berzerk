package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.*;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.model.elements.EnemyBullet;
import com.l01gr05.berzerk.mvc.model.elements.PowerUp;

import java.io.IOException;
import java.util.List;

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
        if (action == GUI.INPUT.ACTIVATE) togglePowerUp(game);
        if (action == GUI.INPUT.NONE) move(getModel().getAgent().getPosition(), game);
    }

    private void togglePowerUp(Game game) {
        Agent agent = getModel().getAgent();
        PowerUp p = agent.getPowerUp();

        if (p == null) return;

        p.switchPowerUp(agent);
        game.setPowerUp(p);

        if (!p.isEnabled()) { agent.setState(new AgentNormal()); return; }

        switch (p.getType()) {
            case "Laser":
                agent.setState(new AgentLaser());
                break;
            case "Shield":
                agent.setState(new AgentShield());
                break;
            case "Cannon":
                agent.setState(new AgentCannon());
                break;
            default:
                agent.setState(new AgentNormal());
                break;
        }
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
        Agent agent = getModel().getAgent();
        agent.shoot(game, getModel());
        agent.setState(new AgentNormal());
    }

    public void move(Position position, Game game) throws IOException {
        Arena arena = getModel();
        Agent agent = arena.getAgent();

        if (arena.getKey() == null) arena.setOpen();

        if (!(agent.getState() instanceof AgentShield)) {
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
            game.nextLevel(); return;
        }

        if (arena.isKey(position)) {
            arena.removeKey();
            arena.setOpen();
        }

       if (arena.isPowerUp(position)) {
            PowerUp p = arena.getPowerUp(position);
            agent.setPowerUp(p);
            game.setPowerUp(p);
            arena.removePowerUp(position);
        }

       agent.setPosition(position);
    }

    private void gotHit(Game game, Agent agent) {
        game.decreaseLives();

        if (game.isGameOver()) game.showStartMenu();

        agent.setPosition(agent.getInitialPosition());
        agent.setState(new AgentNormal());
    }
}
