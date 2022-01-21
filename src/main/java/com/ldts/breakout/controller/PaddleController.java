package com.ldts.breakout.controller;

import com.ldts.breakout.Constants;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Paddle;
import com.ldts.breakout.model.Position;

public class PaddleController {

    private Paddle paddle;

    public PaddleController(Paddle paddle){this.paddle = paddle;}

    public Position moveLeft() {return new Position(paddle.getPosition().getX() - 1, paddle.getPosition().getY());}
    public Position moveRight() {return new Position(paddle.getPosition().getX() + 1, paddle.getPosition().getY());}

    private boolean canPaddleMove(Position position){
        if (position.getX() > Constants.WIDTH - Constants.PADDLE_WIDTH - 1) return false;
        return position.getX() > 0;
    }

    public void movePaddle(Position position){
        if(canPaddleMove(position))
            paddle.setPosition(position);
    }

    public Position doAction(GUI.ACTION action){
        if(action == GUI.ACTION.LEFT) return moveLeft();
        if(action == GUI.ACTION.RIGHT) return moveRight();
        return null;
    }

    public Paddle getPaddle() {return paddle;}
    public void setPaddle(Paddle paddle) {this.paddle = paddle;}

    public void addPoints(int value){paddle.updatePoints(value);}

    public void lostLife(){paddle.updateLives();}


}
