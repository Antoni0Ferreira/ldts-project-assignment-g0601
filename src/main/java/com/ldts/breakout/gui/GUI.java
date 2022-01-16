package com.ldts.breakout.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.breakout.Position;

import java.io.IOException;

public interface GUI {

    enum ACTION{
        LEFT,RIGHT,QUIT,UP,DOWN,CHOOSE
    }

    TextGraphics createTextGraphics();

    int getWidth();
    int getHeight();

    void drawBackground();

    void clear() throws IOException;

    void refresh() throws IOException;

    void close() throws IOException;

    void drawBall(Position position);

    void drawPaddle(Position position);

    void drawInfo(int points, int lives);

    void drawWall(Position position);

    void drawBrick(int points, Position position);

    void drawButton(Position bPos, Position tPos, String text, String textColor);

    void drawTitle(Position position, String text, String color);

}
