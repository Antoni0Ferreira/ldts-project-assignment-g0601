package com.ldts.breakout.model;
import com.ldts.breakout.Constants;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import static com.googlecode.lanterna.Symbols.FACE_BLACK;

public class Ball extends Element {
    private int dirX, dirY;
    private boolean destroyedBrick = false;
    private boolean lostLife = true;

    public Ball() {
        super(Constants.INIT_BALL_X,Constants.INIT_BALL_Y);
        this.dirX = 1;
        this.dirY = 1;
    }

    public Ball(Position position, int dirX, int dirY){ super(position);
        this.dirX = dirX;
        this.dirY = dirY;
    }

    public void move(){

        if(getPosition().getX() <= Constants.BORDER_LEFT_X) {
            dirX = -dirX;
        }
        if(getPosition().getX() >= Constants.BORDER_RIGHT_X)
            dirX = -dirX;

        if(getPosition().getY() <= Constants.BORDER_TOP_Y){
            dirY = -dirY;
            setDestroyedBrick(false);
        }
        if(getPosition().getY() >= Constants.BORDER_BOTTOM_Y){
            dirY = -dirY;
            setDestroyedBrick(false);
        }

        getPosition().setX(getPosition().getX()+dirX);
        getPosition().setY(getPosition().getY()+dirY);}

    public void hitPaddle(){
        dirY = -dirY;
        setDestroyedBrick(false);
    }

    public void hitBrick(){
        dirY = -dirY;
        setDestroyedBrick(true);
    }

    public int getDirX() {
        return dirX;
    }
    public int getDirY() {
        return dirY;
    }

    public void setDirX(int dirX) {this.dirX = dirX;}
    public void setDirY(int dirY) {this.dirY = dirY;}

    public boolean getDestroyedBrick() {
        return destroyedBrick;
    }
    public void setDestroyedBrick(boolean destroyedBrick) {this.destroyedBrick = destroyedBrick;}

    public boolean getLostLife() {return lostLife;}
    public void setLostLife(boolean lostLife) {this.lostLife = lostLife;}
}
