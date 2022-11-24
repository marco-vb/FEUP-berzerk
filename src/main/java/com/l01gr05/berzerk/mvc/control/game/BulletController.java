package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;

public class BulletController extends Controller<Bullet> {
    public BulletController(Bullet model) {
        super(model);
    }

    public void update(Game game, GUI.INPUT action) {
// TO do
    }

    private void moveBullet(Bullet bullet, Position position) {
        if (bullet.getPosition().getX() == position.getX() && bullet.getPosition().getY() == position.getY()) {
            bullet.setPosition(position);
        }
        bullet.setPosition(position);
    }
}