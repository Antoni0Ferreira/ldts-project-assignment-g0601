package com.ldts.breakout.controller;

import com.ldts.breakout.Game;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.model.Ball;
import com.ldts.breakout.Constants;

import java.awt.*;

public class BallController extends GameController{
    private  ArenaController arenaController;
    private long lastMovement;
    private long beginningGameTime;
    private Ball ball;

    public BallController(Arena arena, ArenaController arenaController, Ball ball){
        super(arena);
        this.ball = ball;
        this.arenaController = arenaController;
        this.lastMovement = 0;
        this.beginningGameTime = 0;
    }
/*
    public class BallThread extends Thread{
        @Override
        public void run(){

            try{
                sleep(1000);
                while(!stopThread){
                    sleep(50);
                    draw();
                    arena.hitsPaddle();

                    if(!arena.getBall().getDestroyedBrick())
                        arena.hitsBrick();

                    screen.clear();
                    if(arena.gameLost()){gameEnded(false);}
                    if(arena.gameWon()){gameEnded(true);}
                    arena.getBall().move();
                }
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    public void move(){
        if(ball.getPosition().getX() <= Constants.BORDER_LEFT_X) {
            ball.setDirX(-ball.getDirX());
        }
        if(ball.getPosition().getX() >= Constants.BORDER_RIGHT_X)
            ball.setDirX(-ball.getDirX());

        if(ball.getPosition().getY() <= Constants.BORDER_TOP_Y){
            ball.setDirY(-ball.getDirY());
            ball.setDestroyedBrick(false);
        }
        if(ball.getPosition().getY() >= Constants.BORDER_BOTTOM_Y){
            ball.setDirY(-ball.getDirY());
            ball.setDestroyedBrick(false);
        }

        ball.getPosition().setX(ball.getPosition().getX()+ball.getDirX());
        ball.getPosition().setY(ball.getPosition().getY()+ ball.getDirY());}

    public Rectangle getRectBall() {
        return new Rectangle(ball.getPosition().getX(),ball.getPosition().getY(),Constants.BALL_WIDTH,Constants.BALL_HEIGHT);
    }

    public void hitPaddle(){
        ball.setDirY(-ball.getDirY());
        ball.setDestroyedBrick(false);
    }

    public void hitBrick(){
        ball.setDirY(-ball.getDirY());
        ball.setDestroyedBrick(true);
    }

    @Override
    public void step(Game game, long time){
        arenaController.hitsPaddle();
        if(!ball.getDestroyedBrick())
            arenaController.hitsBrick();
        move();
    }

    public void resetBall(){
        ball.setPosition(new Position(Constants.INIT_BALL_X, Constants.INIT_BALL_Y));
        ball.setDestroyedBrick(false);
    }
}
