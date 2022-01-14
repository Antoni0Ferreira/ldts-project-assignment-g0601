package com.ldts.breakout.gui;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.breakout.model.Menu;
import com.ldts.breakout.model.MenuOption;
import com.ldts.breakout.model.Position;
import org.w3c.dom.Text;

import java.io.IOException;

public interface GUI {

    enum ACTION{
        LEFT,RIGHT,QUIT,UP,DOWN,CHOOSE
    }

    TextGraphics createTextGraphics();

    int getWidth();
    int getHeight();

    void drawBackground(TextGraphics textGraphics);

    void clear() throws IOException;

    void refresh() throws IOException;

    void close() throws IOException;

    void addKeyBoardListener(KeyBoardObserver observer);

    void drawBall(Position position);

    void drawPaddle(Position position);

    void drawPoints(int numPoints, Position position);
    
    void drawInfo(int points, int lives);

    void drawWall(Position position);

    void drawBrick(int points, Position position);

    void drawButton(Position bPos, Position tPos, String text, String textColor);
    
    void drawTitle(Position position, String text, String color);

    void drawMenuOption(char option, Position position);



}
