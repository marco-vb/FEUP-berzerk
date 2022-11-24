package com.l01gr05.berzerk.mvc.models.game.arena;

import com.l01gr05.berzerk.mvc.models.game.elements.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

public class ArenaLoader {
    private final int level;

    public ArenaLoader(int level) {this.level = level;}

    public Arena load() throws FileNotFoundException {
        Arena arena = new Arena(20, 20);
        arena.setLevel(level);
        List<String> lines = readArenaFile(level);
        setArenaElements(arena, lines);
        return arena;
    }

    private List<String> readArenaFile(int level) throws FileNotFoundException {
        URL path = getClass().getResource("/map/lvl" + level + ".txt");
        assert path != null;
        BufferedReader level_reader = new BufferedReader(new FileReader(path.getFile()));
        return level_reader.lines().toList();
    }

    private Element createElement(char element, int x, int y) {
        switch (element) {
            case 'A' -> {return new Agent(x, y);}
            case 'E' -> {return new Enemy(x, y);}
            case 'W' -> {return new Wall(x, y);}
        }
        return null;
    }
    private void setArenaElements(Arena arena, List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);
                if (c != '.') arena.addElement(createElement(c, j, i));
            }
        }
    }
}
