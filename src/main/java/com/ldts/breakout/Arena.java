package com.ldts.breakout;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;


public class Arena{
    private final Ball ball;
    private final Paddle paddle;
    private final List<Wall> walls;
    private final List<Brick> bricks;
    private final Points points;
    private final boolean ballPaddleCollision = false;

    public Arena(){
        ball = new Ball();
        paddle = new Paddle();
        walls = createWalls();
        bricks = createBricks();
        points = new Points();
    }

    // Only for tests
    public Arena(Paddle paddle){
        ball = new Ball();
        this.paddle = paddle;
        walls = createWalls();
        bricks = new ArrayList<Brick>();
        points = new Points();
    }

    //Only for tests
    public Arena(Paddle paddle,Ball ball){
        this.paddle = paddle;
        this.ball = ball;
        walls = createWalls();
        bricks = new ArrayList<Brick>();
        points = new Points();
    }

    //Only for tests
    public Arena(Ball ball){
        this.ball = ball;
        bricks = new ArrayList<Brick>();
        paddle = new Paddle();
        walls = createWalls();
        points = new Points();
    }

    //Only for tests
    public Arena(Ball ball, List<Brick> bricks){
        this.ball = ball;
        this.bricks = bricks;
        paddle = new Paddle();
        walls = createWalls();
        points = new Points();
    }

    //Only for tests
    public Arena(Points points){
        ball = new Ball();
        bricks = new ArrayList<Brick>();
        paddle = new Paddle();
        walls = createWalls();
        this.points = points;
    }

    public Ball getBall() {
        return ball;
    }

    public int getPoints() {return points.getNumPoints();}


    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        ball.draw(screen);
        paddle.draw(screen);
        points.draw(screen);
        for(Brick brick: bricks){
            if(!brick.isDestroyed())
                brick.draw(screen);
        }
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
            walls.add(new Wall(new Position(c,0)));
            walls.add(new Wall(new Position(c,Constants.HEIGHT - 1)));
        }
        for (int r = 1; r < Constants.HEIGHT - 1; r++) {
            walls.add(new Wall(new Position(0,r)));
            walls.add(new Wall(new Position(Constants.WIDTH - 1, r)));
        }
        return walls;
    }

    public List<Brick> createBricks() {
        ArrayList<Brick> bricks = new ArrayList <>();
        for(int i = 0; i < 5; i++){
            int count = 0;
            for(int j = 0; j < 7; j++){
                bricks.add(new Brick(new Position(j + 1 + count ,i + 4)));
                count += 7;
            }
        }
        return bricks;
    }

    public void hitsPaddle() {
        if(ball.getRect().intersects(paddle.getRect())){
            ball.hitPaddle();
        }
    }

    public void hitsBrick() {
        for(Brick brick: bricks){
            if(ball.getRect().intersects(brick.getRect()) && !brick.isDestroyed()){
                ball.hitBrick();
                brick.setDestroyed(true);
                points.add(brick.getPoints());
            }
        }
    }
}
