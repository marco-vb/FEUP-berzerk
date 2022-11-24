package com.l01gr05.berzerk.mvc.view.game;

import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.model.elements.Bullet;
import com.l01gr05.berzerk.mvc.view.Viewer;

public class BulletViewer extends ElementViewer {
    public BulletViewer(Bullet bullet) {
        super(bullet);
    }

    public void draw(GUI gui) {
        gui.drawBullet(getModel());
    }
}

