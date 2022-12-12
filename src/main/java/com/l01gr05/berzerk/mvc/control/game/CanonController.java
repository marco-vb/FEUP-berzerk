package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.gui.GUI;
import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Canon;

import java.io.IOException;

public class CanonController extends Controller<Arena> {
    public CanonController(Arena arena) { super(arena); }

    @Override
    public void update(Game game, GUI.INPUT action) throws IOException {
        if (getModel().getPowerUps() != null) {
            for (int i = 0; i < getModel().getPowerUps().size(); i++) {
                if (getModel().getPowerUps().get(i) instanceof Canon && getModel().getPowerUps().get(i).getPosition().equals(getModel().getAgent().getPosition())) {
                    getModel().getAgent().setPowerUp(getModel().getPowerUps().get(i));
                    game.setPowerUp(getModel().getAgent().getPowerUp());
                    getModel().getPowerUps().remove(i);
                }
            }
        }
    }
}