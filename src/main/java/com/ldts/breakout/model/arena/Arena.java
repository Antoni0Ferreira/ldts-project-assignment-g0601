package com.ldts.breakout.model.arena;

import com.ldts.breakout.model.*;

import java.util.ArrayList;
import java.util.List;


public class Arena{
    private Ball ball;
    private Paddle paddle;
    private List<Wall> walls;
    private List<Brick> bricks;
    private Points points;

 /*   public Arena() {
        ball = createBall();
        paddle = createPaddle();
        walls = createWalls();
        bricks = createBricks();
        points = createPoints();
    }

    // Only for tests
    public Arena(Paddle paddle) {
        ball = createBall();
        this.paddle = paddle;
        walls = createWalls();
        bricks = createBricks();
        points = createPoints();
    }

    //Only for tests
    public Arena(Paddle paddle, BallModel ball) {
        this.paddle = paddle;
        this.ball = ball;
        walls = createWalls();
        bricks = createBricks();
        points = createPoints();
    }

    //Only for tests
    public Arena(BallModel ball) {
        this.ball = ball;
        bricks = createBricks();
        paddle = createPaddle();
        walls = createWalls();
        points = createPoints();
    }

    //Only for tests
    public Arena(BallModel ball, List<Brick> bricks) {
        this.ball = ball;
        this.bricks = bricks;
        paddle = createPaddle();
        walls = createWalls();
        points = createPoints();
    }

    //Only for tests
    public Arena(Points points) {
        ball = createBall();
        bricks = createBricks();
        paddle = createPaddle();
        walls = createWalls();
        this.points = points;
    }*/

    public Arena(){}

    public void setBall(Ball ball) {this.ball = ball;}

    public void setPaddle(Paddle paddle) {this.paddle = paddle;}

    public void setWalls(List<Wall> walls) {this.walls = walls;}

    public void setBricks(List<Brick> bricks) {this.bricks = bricks;}

    public void setPoints(Points points) {this.points = points;}

    public Ball getBall() {return ball;}

    public Paddle getPaddle() {return paddle;}

    public List<Wall> getWalls() {return walls;}

    public List<Brick> getBricks() {return bricks;}

    public Points getPoints() {return points;}
}
