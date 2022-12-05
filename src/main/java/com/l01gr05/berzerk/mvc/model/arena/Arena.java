package com.l01gr05.berzerk.mvc.model.arena;

import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.elements.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.URL;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private int level;
    private Agent agent;
    private List<Exit> exits;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<Bullet> bullets;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    public Agent getAgent() {return agent;}

    public List<Enemy> getEnemies() {return enemies;}

    public List<Bullet> getBullets() {return bullets;}

    // set level
    public void setLevel(int level) {
        this.level = level;
    }

    // Removes enemy on position
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    public void addElement(Element element) {
        if (element instanceof Agent) {
            agent = (Agent) element;
        } else if (element instanceof Exit) {
            exits.add((Exit) element);
        } else if (element instanceof Enemy) {
            enemies.add((Enemy) element);
        } else if (element instanceof Wall) {
            walls.add((Wall) element);
        }
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public List<Element> getElements() {
        List<Element> elements = new ArrayList<>();
        elements.add(agent);
        elements.addAll(exits);
        elements.addAll(bullets);
        elements.addAll(enemies);
        elements.addAll(walls);
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

    public boolean isAgent(Position position) {
        return agent.getPosition().equals(position);
    }

    public int getScore() {
        return agent.getScore();
    }

    public int getLives() {
        return agent.getLives();
    }
}
