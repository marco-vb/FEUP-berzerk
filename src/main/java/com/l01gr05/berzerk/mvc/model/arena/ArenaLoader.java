package com.l01gr05.berzerk.mvc.model.arena;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class ArenaLoader {
    private final int level;
    private final Game game;

    public ArenaLoader(int level, Game game) {
        this.level = level;
        this.game = game;
    }

    public Arena load() throws IOException {
        Arena arena = new Arena(30, 20, level, game);
        arena.setLevel(level);
        List<String> lines = readArenaFile(level);
        setArenaElements(arena, lines);
        return arena;
    }

    private List<String> readArenaFile(int level) throws IOException {
        createRandomLevel(level);
        URL path = getClass().getResource("/levels/lvl" + level + ".txt");
        assert path != null;
        BufferedReader level_reader = new BufferedReader(new FileReader(path.getFile()));
        return level_reader.lines().collect(Collectors.toList());
    }

    private void createRandomLevel(int level) throws IOException {
        // open file for writing
        URL path = getClass().getResource("/levels/lvl" + level + ".txt");
        assert path != null;
        BufferedWriter level_writer = new BufferedWriter(new FileWriter(path.getFile()));
        char[][] grid = new char[20][30];
        createWalls(grid);
        createExit(grid);
        createRandomBoxes(grid);
        createRandomEnemies(grid);
        //createRandomPowerUps(grid);
        spawnPlayer(grid);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                level_writer.write(grid[i][j]);
            }
            level_writer.write("\n");
        }
        level_writer.close();
    }

    private void createExit(char[][] grid) {
    }

    private void spawnPlayer(char[][] grid) {
    }

    private void createRandomEnemies(char[][] grid) {
    }

    private void createRandomBoxes(char[][] grid) {
    }

    private void createWalls(char[][] grid) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == 0 || i == 19 || j == 0 || j == 29) grid[i][j] = '#';
                else grid[i][j] = ' ';
            }
        }
    }

    private Element createElement(char element, int x, int y) {
        switch (element) {
            case 'A': return new Agent(new Position(x, y));
            case 'X': return new Exit(new Position(x, y));
            case 'E': return new Enemy(new Position(x, y));
            case '#': return new Wall(new Position(x, y));
        }
        return null;
    }
    private void setArenaElements(Arena arena, List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);
                if (c != '.') {
                    arena.addElement(createElement(c, j, i));
                }
            }
        }
    }
}
