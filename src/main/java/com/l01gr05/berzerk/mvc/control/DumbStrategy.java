package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Element;

public class DumbStrategy implements MoveStrategy {
    @Override
    public void move(Element element, Arena arena) {
        Position position = element.getPosition().getRandom();
        if (!(arena.isWall(position)|| arena.isEnemy(position) || arena.isExit(position))) {
            element.setPosition(position);
        }
    }
}
