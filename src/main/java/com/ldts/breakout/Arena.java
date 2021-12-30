package com.ldts.breakout;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;


public class Arena {
    private final Ball ball;
    private final Paddle paddle;
    private final List<Wall> walls;

    public Arena(){
        ball = new Ball();
        paddle = new Paddle();
        this.walls = createWalls();
    }

    // Only for tests
    public Arena(Paddle paddle){
        ball = new Ball();
        this.paddle = paddle;
        this.walls = createWalls();
    }

    public Ball getBall() {
        return ball;
    }


    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        paddle.draw(screen);
        ball.draw(screen);
        for(Wall wall: walls){
            wall.draw(screen);
        }
    }

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

    private List<Wall> createWalls() {
        ArrayList<Wall> walls = new ArrayList <>();
        for (int c = 0; c < Constants.WIDTH; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, Constants.HEIGHT - 1));
        }
        for (int r = 1; r < Constants.HEIGHT - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(Constants.WIDTH - 1, r));
        }
        return walls;
    }
}
