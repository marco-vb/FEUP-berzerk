package com.l01gr05.berzerk.states;

import com.l01gr05.berzerk.mvc.control.Controller;
import com.l01gr05.berzerk.mvc.control.game.MapController;
import com.l01gr05.berzerk.mvc.model.map.Map;
import com.l01gr05.berzerk.mvc.view.Viewer;
import com.l01gr05.berzerk.mvc.view.game.MapViewer;

public class GameState extends State<Map> {
    public GameState(Map map) {
        super(map);
    }

    @Override
    protected Controller<Map> getController() {
        return new MapController(getModel());
    }

    @Override
    protected Viewer<Map> getViewer() {
        return new MapViewer(getModel());
    }
}
