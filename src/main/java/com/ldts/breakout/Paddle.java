package com.ldts.breakout;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;

import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;

public class Paddle extends Element{

    public Paddle() { super(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y); }

    public Paddle(Position position) { super(position); }


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF7000"));
        graphics.drawRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);
    }

    Rectangle getRect() {
        return new Rectangle(getPosition().getX(),getPosition().getY(),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT);
    }
}