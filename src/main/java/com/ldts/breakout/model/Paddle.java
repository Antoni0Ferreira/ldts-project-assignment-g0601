package com.ldts.breakout.model;

import com.ldts.breakout.Constants;

public class Paddle extends Element {
    private int points,lives;

    public Paddle() {
        super(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y);
        points = 0;
        lives = 3;
    }

    public int getPoints() {return points;}
    public void updatePoints(int value) {points += value;}

    public int getLives() {return lives;}
    public void updateLives(){this.lives -= 1;}
    public void setLives(int lives){ this.lives = lives;}
    public void setPoints(int points){this.points = points;}
}