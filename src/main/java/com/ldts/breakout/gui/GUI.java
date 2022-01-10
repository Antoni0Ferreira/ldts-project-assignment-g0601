package com.ldts.breakout.gui;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.ldts.breakout.Position;

import java.io.IOException;

public interface GUI {

    enum ACTION{
        LEFT,RIGHT
    }

    TextGraphics createTextGraphics();

    int getWidth();
    int getHeight();

    void drawBackground(TextGraphics textGraphics);

    void clear() throws IOException;

    void refresh() throws IOException;

    void close() throws IOException;

    void addKeyBoardListener(KeyBoardObserver obs)

    void drawBall(Position position, TextGraphics textGraphics);

    void drawPaddle(Position position, TextGraphics textGraphics);

    void drawPoints(int numPoints, Position position, TextGraphics textGraphics);

    void drawWall(Position position, TextGraphics textGraphics);

    void drawBrick(int points, Position position, TextGraphics textGraphics);

}
