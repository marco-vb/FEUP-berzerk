package com.l01gr05.berzerk.gui;

import java.io.IOException;

public interface GUI {
    void clear() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;

    INPUT getInput() throws IOException;

    enum INPUT {UP, DOWN, LEFT, RIGHT, NONE, QUIT, ENTER, SHOOT};
}
