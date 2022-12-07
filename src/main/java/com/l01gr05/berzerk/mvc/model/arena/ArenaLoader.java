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

    private Position exit;

    public ArenaLoader(int level, Game game) {
        this.level = level;
        this.game = game;
        this.exit = new Position(0, 0);
    }

    public Arena load() throws IOException {
        Arena arena = new Arena(Game.WIDTH, Game.HEIGHT, level, game);
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
        char[][] grid = new char[Game.HEIGHT][Game.WIDTH];
        int x_offset = (Game.WIDTH - 6) / 5;
        int y_offset = (Game.HEIGHT - 4) / 3;
        int dx[] = new int[8];
        int dy[] = new int[8];
        for (int i = 0; i < 8; i++) {
            dx[i] = (i/2+1) * (x_offset+1);
            dy[i] = (i % 2 == 0) ? y_offset + 1 : 2 * y_offset + 2;
        }
        createWalls(grid, dx, dy);
        createExit(grid, dx, dy);
        createRandomEnemies(grid);
        //createRandomPowerUps(grid);
        spawnPlayer(grid);
        for (int i = 0; i < Game.HEIGHT; i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                level_writer.write(grid[i][j]);
            }
            level_writer.write("\n");
        }
        level_writer.close();
    }

    private void createExit(char[][] grid, int[] dx, int[] dy) {
        Random random = new Random();
        int wall = random.nextInt(4);
        int x = 0, y = 0;
        int sign = random.nextInt(2) * 2 - 1;
        switch (wall) {
            case 0:
                x = dx[random.nextInt(dx.length)] + 2 * sign;
                break;
            case 1:
                y = Game.HEIGHT - 1; x = dx[random.nextInt(dx.length)] + 2 * sign;
                break;
            case 2:
                y = dy[random.nextInt(dy.length)] + sign * 2;
                break;
            case 3:
                x = Game.WIDTH - 1; y = dy[random.nextInt(dy.length)] + sign * 2;
                break;
        }
        for (int i = 0; i < 3; i++){
            if (wall <= 1) grid[y][x+ i*sign] = 'X';
            else grid[y + i*sign][x] = 'X';
        }
        this.exit = new Position(x, y);
    }

    private void spawnPlayer(char[][] grid) {
        int distances_from_exit[][] = new int[Game.HEIGHT][Game.WIDTH];
        for (int i = 0; i < Game.HEIGHT; i++)
            for (int j = 0; j < Game.WIDTH; j++) distances_from_exit[i][j] = -1;

        Position p = bfs_distances(distances_from_exit, grid, exit.getX(), exit.getY());
        int x = p.getX(), y = p.getY();
        if (x == 1) x = 2; if (x == Game.WIDTH - 2) x = Game.WIDTH - 3;
        if (y == 1) y = 2; if (y == Game.HEIGHT - 2) y = Game.HEIGHT - 3;

        grid[y][x] = 'A';
    }

    private Position bfs_distances(int[][] distances_from_exit, char[][] grid, int x, int y) {
        int dx[] = new int[]{-1, 0, 1, 0};
        int dy[] = new int[]{0, -1, 0, 1};
        int queue[][] = new int[Game.HEIGHT * Game.WIDTH][2];
        int head = 0, tail = 0;
        queue[tail][0] = x; queue[tail][1] = y; tail++;
        distances_from_exit[y][x] = 0;
        while (head < tail) {
            int cx = queue[head][0], cy = queue[head][1];
            head++;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx < 0 || nx >= Game.WIDTH || ny < 0 || ny >= Game.HEIGHT || grid[ny][nx] == '#') continue;
                if (distances_from_exit[ny][nx] == -1) {
                    distances_from_exit[ny][nx] = distances_from_exit[cy][cx] + 1;
                    queue[tail][0] = nx; queue[tail][1] = ny; tail++;
                }
            }
        }
        int max_distance = 0;
        for (int i = 0; i < Game.HEIGHT; i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                if (grid[i][j] != '#' && distances_from_exit[i][j] > max_distance) {
                    max_distance = distances_from_exit[i][j];
                    x = j; y = i;
                }
            }
        }
        return new Position(x, y);
    }

    private void createRandomEnemies(char[][] grid) {
        grid [1][1] = 'E';
    }

    private void createWalls(char[][] grid, int dx[], int dy[]) {
        for (int i = 0; i < Game.HEIGHT; i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                if (i == 0 || i == 18 || j == 0 || j == 30) grid[i][j] = '#';
                else grid[i][j] = ' ';
            }
        }
        // Makes 8 central pillars of the arena
        for (int i = 0; i < 8; i++) grid[dy[i]][dx[i]] = '#';

        createRandomWalls(grid, dx, dy);
    }

    private void createRandomWalls(char[][] grid, int[] dx, int[] dy) {
        for (int i = 0; i < dx.length; i++) {
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
