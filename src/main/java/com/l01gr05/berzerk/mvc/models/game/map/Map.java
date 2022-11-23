package com.l01gr05.berzerk.models.game.map;

import com.l01gr05.berzerk.models.game.elements.Agent;
import com.l01gr05.berzerk.models.game.elements.Enemy;
import com.l01gr05.berzerk.models.game.elements.Wall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.URL;
import java.io.FileReader;
import java.util.List;

public class Map {
    private int width;
    private int height;
    private int level;
    private Agent agent;
    private List<Enemy> enemies;
    private List<Wall> walls;


    public Map(int width, int height, int level) throws FileNotFoundException {
        this.width = width;
        this.height = height;
        this.level = level;
        URL path = getClass().getResource("/map/lvl" + level + ".txt");
        assert path != null;
        BufferedReader level_reader = new BufferedReader(new FileReader(path.getFile()));
        List<String> lines = level_reader.lines().toList();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                switch (lines.get(i).charAt(j)) {
                    case 'A' -> agent = new Agent(j, i);
                    case 'E' -> enemies.add(new Enemy(j, i));
                    case 'W' -> walls.add(new Wall(j, i));
                }
            }
        }
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getLevel() {return level;}

    public Agent getAgent() {return agent;}

    public List<Enemy> getEnemies() {return enemies;}

    public List<Wall> getWalls() {return walls;}
}
