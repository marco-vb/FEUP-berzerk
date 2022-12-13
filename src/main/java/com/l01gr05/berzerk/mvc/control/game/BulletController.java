package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;

public class BulletController extends Controller<Arena> {

    public BulletController(Arena arena) {
        super(arena);
    }

    public void update(Game game, GUI.INPUT action) {
        for (int i = 0; i < getModel().getBullets().size(); i++) {
           Bullet bullet = getModel().getBullets().get(i);
           if (bullet.getDirection() == 'N') move(bullet, bullet.getPosition().getUp(), game);
           if (bullet.getDirection() == 'S') move(bullet, bullet.getPosition().getDown(), game);
           if (bullet.getDirection() == 'W') move(bullet, bullet.getPosition().getLeft(), game);
           if (bullet.getDirection() == 'E') move(bullet, bullet.getPosition().getRight(), game);
       }

    }

    private void move(Bullet bullet, Position position, Game game) {
        // make random number between 0, 1, 2
        int random = (int) (Math.random() * 3);
        if (getModel().isWall(position) || getModel().isTower(position) || getModel().isExit(position)) {
            getModel().removeBullet(bullet);
        }

        if (bullet instanceof AgentBullet && getModel().isEnemy(position)) {
            game.playDeathSound();
            getModel().removeBullet(bullet);
            for (int i = 0; i < getModel().getEnemies().size(); i++) {
                if (getModel().getEnemies().get(i).getPosition().equals(position)) {
                    if (random == 0) {
                        getModel().addPowerUp(new Shield(getModel().getEnemies().get(i).getPosition()));
                    }
                    else if (random == 1) {
                        getModel().addPowerUp(new Cannon(getModel().getEnemies().get(i).getPosition()));
                    }
                    else if (random == 2){
                        getModel().addPowerUp(new Laser(getModel().getEnemies().get(i).getPosition()));
                    }
                    getModel().removeEnemy(getModel().getEnemies().get(i));
                }
            }
            game.setScore(game.getScore() + 10);
        }

        else if (getModel().isAgent(position) && (bullet instanceof EnemyBullet)) {
            Agent agent = getModel().getAgent();
            if (agent.getPowerUp() instanceof Shield && agent.getPowerUp().isEnabled()) {
                getModel().removeBullet(bullet);
                agent.setPowerUp(null);
                game.setPowerUp(agent.getPowerUp());
                return;
            } else {
                getModel().removeBullet(bullet);
                game.decreaseLives();
            }
            if (game.isGameOver()) {
                game.showDeathMenu();
                agent.setPowerUp(null);
                game.setPowerUp(agent.getPowerUp());
            };
            agent.setPosition(agent.getInitialPosition());
        }
        else {
            bullet.setPosition(position);
        }
    }
}