import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import javax.crypto.Cipher;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final Ball ball;
    private final Paddle paddle;
    private final List<Brick> bricks;
    private final List<Wall> walls;

    public Arena() {
        ball = new Ball(Constants.INIT_BALL_X,Constants.INIT_BALL_Y);
        paddle = new Paddle(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y);
        this.walls = createWalls();
        this.bricks = createBricks();

    }

    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        ball.draw(screen);
        paddle.draw(screen);
        for(Brick brick: bricks){
            if(!brick.isDestroyed())
                brick.draw(screen);
        }
        for(Wall wall: walls){
            wall.draw(screen);

        }
    }

    private boolean canPaddleMove(Position position){
        if (position.getX() > Constants.WIDTH - Constants.PADDLE_WIDTH - 1) return false;
        return position.getX() >= 1;
    }

    public void movePaddle(Position position){
        if(canPaddleMove(position))
            paddle.setPosition(position);
    }

    public void processKey(KeyStroke key){
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowRight) {
            movePaddle(paddle.moveRight());
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            movePaddle(paddle.moveLeft());
        }
    }

    private List<Brick> createBricks() {
        ArrayList<Brick> bricks = new ArrayList <>();
        for(int i = 0; i < 5; i++){
            int count = 0;
            for(int j = 0; j < 7; j++){
                bricks.add(new Brick(j + 1 + count ,i + 4));
                count += 7;
            }
        }
        return bricks;
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
