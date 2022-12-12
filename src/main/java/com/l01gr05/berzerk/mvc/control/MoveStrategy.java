package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Element;

public interface MoveStrategy {
    void move(Element element, Arena arena);
}