package com.ldts.breakout;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;

import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;


public class Brick extends Element {

    private boolean destroyed;
    private int points;

    public Brick(Position position) {
        super(position);
        switch (getPosition().getY()) {
            case 4 -> points = Constants.RED_BRICK;
            case 5 -> points = Constants.DARK_ORANGE_BRICK;
            case 6 -> points = Constants.LIGHT_ORANGE_BRICK;
            case 7 -> points = Constants.YELLOW_BRICK;
            case 8 -> points = Constants.BLUE_BRICK;
        }
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    public int getPoints(){
        return points;
    }

    //Only for tests
    public void setPoints(int points){
        this.points = points;
    }

    @Override
    public void draw(TextGraphics graphics) {

        switch (points){
            case Constants.RED_BRICK -> graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            case Constants.DARK_ORANGE_BRICK -> graphics.setForegroundColor(TextColor.Factory.fromString("#FF8700"));
            case Constants.LIGHT_ORANGE_BRICK -> graphics.setForegroundColor(TextColor.Factory.fromString("#FFC100"));
            case Constants.YELLOW_BRICK -> graphics.setForegroundColor(TextColor.Factory.fromString("#CDFF00"));
            case Constants.BLUE_BRICK -> graphics.setForegroundColor(TextColor.Factory.fromString("#008FFF"));
        }
        graphics.drawRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-');
    }

    Rectangle getRect() {
        return new Rectangle(getPosition().getX(),getPosition().getY(),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT);
    }
}