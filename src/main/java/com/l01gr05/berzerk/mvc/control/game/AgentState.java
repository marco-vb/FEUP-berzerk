package com.l01gr05.berzerk.mvc.control.game;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.arena.Arena;


public interface AgentState {
    void shoot(Game game, Arena model);
}
