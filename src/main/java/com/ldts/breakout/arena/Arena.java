package com.ldts.breakout.arena;

import com.ldts.breakout.Ball;
import com.ldts.breakout.Brick;
import com.ldts.breakout.Paddle;
import com.ldts.breakout.Wall;

import java.util.List;

public class Arena {

    private Ball ball;
    private Paddle paddle;
    private List<Wall> walls;
    private List<Brick> bricks;

    public Arena(){}

    public void setBall(Ball ball) {this.ball = ball;}

    public void setPaddle(Paddle paddle) {this.paddle = paddle;}

    public void setWalls(List<Wall> walls) {this.walls = walls;}

    public void setBricks(List<Brick> bricks) {this.bricks = bricks;}

    public Ball getBall() {return ball;}

    public Paddle getPaddle() {return paddle;}

    public List<Wall> getWalls() {return walls;}

    public List<Brick> getBricks() {return bricks;}

}
