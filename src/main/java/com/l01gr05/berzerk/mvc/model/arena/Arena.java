package com.l01gr05.berzerk.mvc.model.arena;

import com.l01gr05.berzerk.mvc.model.elements.Agent;
import com.l01gr05.berzerk.mvc.model.elements.Enemy;
import com.l01gr05.berzerk.mvc.model.elements.Wall;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.URL;
import java.io.FileReader;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private int level;
    private Agent agent;
    private List<Enemy> enemies;
    private List<Wall> walls;


    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getLevel() {return level;}

    public Agent getAgent() {return agent;}

    public List<Enemy> getEnemies() {return enemies;}

    public List<Wall> getWalls() {return walls;}

    // set level
    public void setLevel(int level) {
        this.level = level;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void addElement(Object element) {
        if (element instanceof Agent) {
            agent = (Agent) element;
        } else if (element instanceof Enemy) {
            enemies.add((Enemy) element);
        } else if (element instanceof Wall) {
            walls.add((Wall) element);
        }
    }
}
