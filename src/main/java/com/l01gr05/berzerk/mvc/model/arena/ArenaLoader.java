package com.l01gr05.berzerk.mvc.model.arena;

import com.l01gr05.berzerk.Game;
import com.l01gr05.berzerk.mvc.model.Position;
import com.l01gr05.berzerk.mvc.model.arena.Arena;
import com.l01gr05.berzerk.mvc.model.elements.*;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ArenaLoader {
    private final int level;
    private final Game game;

    public ArenaLoader(int level, Game game) {
        this.level = level;
        this.game = game;
    }

    public Arena load() throws IOException {
        Arena arena = new Arena(31, 19, level, game);
        arena.setLevel(level);
        List<String> lines = readArenaFile(level);
        setArenaElements(arena, lines);
        return arena;
    }

    private List<String> readArenaFile(int level) throws IOException {
        createRandomLevel(level);
        URL path = getClass().getResource("/levels/lvlaux.txt");
        assert path != null;
        BufferedReader level_reader = new BufferedReader(new FileReader(path.getFile()));
        return level_reader.lines().collect(Collectors.toList());
    }

    private void createRandomLevel(int level) throws IOException {
        // open file for writing
        URL path = getClass().getResource("/levels/lvlaux.txt");
        assert path != null;
        BufferedWriter level_writer = new BufferedWriter(new FileWriter(path.getFile()));
        char[][] grid = new char[19][31];
        createWalls(grid);
        createExit(grid);
        createRandomBoxes(grid);
        createRandomEnemies(grid);
        //createRandomPowerUps(grid);
        spawnPlayer(grid);
        for (int i = 0; i <= 18; i++) {
            for (int j = 0; j <= 30; j++) {
                level_writer.write(grid[i][j]);
            }
            level_writer.write("\n");
        }
        level_writer.close();
    }

    private void createExit(char[][] grid) {
        grid[0][15] = 'X';
        grid[0][16] = 'X';
        grid[0][17] = 'X';
    }

    private void spawnPlayer(char[][] grid) {
        grid[17][15] = 'A';
    }

    private void createRandomEnemies(char[][] grid) {
        grid [1][1] = 'E';
    }

    private void createRandomBoxes(char[][] grid) {
        int dx[] = {6, 6, 12, 12, 18, 18, 24, 24};
        int dy[] = {6, 12, 6, 12, 6, 12, 6, 12};
        int wall_size = 6;

        for (int i = 0; i < 8; i++) {
            int x = dx[i];
            int y = dy[i];
            char[] directions = {'N', 'S', 'E', 'W'};
            Random random = new Random();
            char direction = directions[random.nextInt(directions.length)];
            int new_x = 0, new_y = 0;
            if (direction == 'N') new_y--;
            if (direction == 'S') new_y++;
            if (direction == 'E') new_x++;
            if (direction == 'W') new_x--;

            while (grid[y + new_y][x + new_x] != '#') {
                grid[y + new_y][x + new_x] = '#';
                x += new_x;
                y += new_y;
            }
        }
    }

    private void createWalls(char[][] grid) {
        for (int i = 0; i <= 18; i++) {
            for (int j = 0; j <= 30; j++) {
                if (i == 0 || i == 18 || j == 0 || j == 30) grid[i][j] = '#';
                else grid[i][j] = ' ';
            }
        }

        int dx[] = {6, 6, 12, 12, 18, 18, 24, 24};
        int dy[] = {6, 12, 6, 12, 6, 12, 6, 12};

        // Makes 8 central pillars of the arena
        for (int i = 0; i < 8; i++) {
            grid[dy[i]][dx[i]] = '#';
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
