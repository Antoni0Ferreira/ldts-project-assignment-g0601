package com.ldts.breakout.controller;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.model.Ball;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.arena.Arena;

import java.awt.*;

public class BallController {
    private Ball ball;
    private long start;

    public BallController(Ball ball){
        this.ball = ball;
    }

    public void startBallTimer(){
        this.start = System.currentTimeMillis();
    }

    public void move() {
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        if (elapsedTime > 1000) {
            if (ball.getPosition().getX() <= Constants.BORDER_LEFT_X) {
                ball.setDirX(-ball.getDirX());
            }
            if (ball.getPosition().getX() >= Constants.BORDER_RIGHT_X)
                ball.setDirX(-ball.getDirX());

            if (ball.getPosition().getY() <= Constants.BORDER_TOP_Y) {
                ball.setDirY(-ball.getDirY());
                ball.setDestroyedBrick(false);
            }
            if (ball.getPosition().getY() >= Constants.BORDER_BOTTOM_Y) {
                ball.setDirY(-ball.getDirY());
                ball.setDestroyedBrick(false);
            }

            ball.getPosition().setX(ball.getPosition().getX() + ball.getDirX());
            ball.getPosition().setY(ball.getPosition().getY() + ball.getDirY());}
    }


    public Rectangle getRectBall() {
        return new Rectangle(ball.getPosition().getX(),ball.getPosition().getY(),1,1);
    }

    public void hitPaddle(){
        ball.setDirY(-ball.getDirY());
        ball.setDestroyedBrick(false);
    }

    public void hitBrick(){
        ball.setDirY(-ball.getDirY());
        ball.setDestroyedBrick(true);
    }

    public void resetBall(){
        ball.setPosition(new Position(Constants.INIT_BALL_X, Constants.INIT_BALL_Y));
        ball.setDestroyedBrick(false);
        start= System.currentTimeMillis();
    }

    public Ball getBall(){
        return ball;
    }

    public void setBall(Ball ball){ this.ball = ball;}
}
