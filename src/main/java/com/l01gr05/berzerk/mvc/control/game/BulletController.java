package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;

public class BulletController extends Controller<Arena> {
    public BulletController(Arena arena) {
        super(arena);
    }

    public void update(Game game, GUI.INPUT action) {
       for (int i = 0; i < getModel().getBullets().size(); i++) {
           Bullet bullet = getModel().getBullets().get(i);
           if (bullet.getDirection() == 'N') move(bullet, bullet.getPosition().getUp());
           if (bullet.getDirection() == 'S') move(bullet, bullet.getPosition().getDown());
           if (bullet.getDirection() == 'W') move(bullet, bullet.getPosition().getLeft());
           if (bullet.getDirection() == 'E') move(bullet, bullet.getPosition().getRight());
       }

    }

    private void move(Bullet bullet, Position position) {
        if (getModel().isWall(position)) {
            getModel().removeBullet(bullet);
        }

        if (getModel().isEnemy(position)) {
            getModel().removeBullet(bullet);
            for (int i = 0; i < getModel().getEnemies().size(); i++) {
                if (getModel().getEnemies().get(i).getPosition().equals(position)) {
                    getModel().removeEnemy(getModel().getEnemies().get(i));
                }
            }
            getModel().getAgent().setScore(getModel().getAgent().getScore() + 10);
        }

        else bullet.setPosition(position);
    }
}