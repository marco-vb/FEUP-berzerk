package com.l01gr05.berzerk.states;

import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.control.game.ArenaController;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.ArenaViewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new ArenaViewer(getModel());
    }
}
