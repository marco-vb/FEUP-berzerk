package com.l01gr05.berzerk.mvc.control;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.Element;

public class SmartStrategy implements MoveStrategy {
    public void move(Element element, Arena arena) {
        double chance = Math.random();
        Position position;

        if (chance < 0.2) {
            position = element.getPosition().getClosest(arena.getAgent().getPosition());
        } else {
            position = element.getPosition().getRandom();
        }

        if (!(arena.isWall(position) || arena.isEnemy(position) || arena.isExit(position))) {
            element.setPosition(position);
        }
    }
}
