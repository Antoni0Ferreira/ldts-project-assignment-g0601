package com.ldts.breakout.model;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.breakout.Constants;

import java.awt.*;

import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;

public class Paddle extends Element {
    private int points,lives;

    public Paddle() {
        super(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y);
        points = 0;
        lives = 3;
    }

    // Only for tests
    public Paddle(Position position) {
        super(position);
        points = 0;
        lives = 3;
    }

    public int getPoints() {return points;}
    public void updatePoints(int value) {points += value;}

    public int getLives() {return lives;}
    public void updateLives(){lives -= 1;}

    public Rectangle getRect() {
        return new Rectangle(getPosition().getX(),getPosition().getY(),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT);
    }
}