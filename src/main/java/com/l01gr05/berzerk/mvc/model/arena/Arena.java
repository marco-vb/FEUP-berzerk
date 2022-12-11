package com.l01gr05.berzerk.mvc.model.arena;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Arena {
    private Game game;
    private Agent agent;
    private List<Exit> exits;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<Bullet> bullets;
    private List<Tower> towers;
    private Key key = null;

    public Arena(Game game) {
        this.game = game;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    public Game getGame() {
        return game;
    }
    public Agent getAgent() {return agent;}

    public List<Enemy> getEnemies() {return enemies;}

    public List<Bullet> getBullets() {return bullets;}

    public List<Tower> getTowers() {return towers;}

    // Removes enemy on position
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    public Wall getWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return wall;
        return null;
    }

    public void removeWall(Position position) {
        walls.remove(getWall(position));
    }

    public void addElement(Element element) {
        if (element instanceof Agent) {
            agent = (Agent) element;
        } else if (element instanceof Exit) {
            exits.add((Exit) element);
        } else if (element instanceof Enemy) {
            enemies.add((Enemy) element);
        } else if (element instanceof Bullet) {
            bullets.add((Bullet) element);
        }
        else if (element instanceof Wall) {
            walls.add((Wall) element);
        } else if (element instanceof Tower) {
            towers.add((Tower) element);
        } else if (element instanceof Key) {
            key = (Key) element;
        }
    }

    public List<Element> getElements() {
        List<Element> elements = new ArrayList<>();
        elements.add(agent);
        elements.addAll(exits);
        elements.addAll(bullets);
        elements.addAll(enemies);
        elements.addAll(walls);
        elements.addAll(towers);
        if (key != null) elements.add(key);
        return elements;
    }

    public boolean isWall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnemy(Position position) {
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExit(Position position) {
        for (Exit exit : exits) {
            if (exit.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTower(Position position) {
        for (Tower tower : towers)
            if (tower.getPosition().equals(position)) return true;
        return false;
    }

    public boolean isKey(Position position) {
        if (key != null) {
            return key.getPosition().equals(position);
        }
        return false;
    }

    public Key getKey() {
        return key;
    }

    public void setOpen() {
        for (Exit exit : exits) {
            removeWall(exit.getPosition());
        }
    }

    public void removeKey() {
        key = null;
    }

    public boolean isAgent(Position position) {
        return agent.getPosition().equals(position);
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Exit> getExits() {
        return exits;
    }
}
