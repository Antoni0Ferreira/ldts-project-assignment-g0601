package com.ldts.breakout.model.arena;

import com.ldts.breakout.Constants;
import com.ldts.breakout.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaLoader extends ArenaBuilder{

    public ArenaLoader(){}

    @Override
    protected Ball createBall(){
        return new Ball();
    }

    @Override
    protected Paddle createPaddle(){
        return new Paddle();
    }

    @Override
    protected List<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList <>();
        for (int c = 0; c < Constants.WIDTH; c++) {
            walls.add(new Wall(new Position(c,0)));
            walls.add(new Wall(new Position(c, Constants.HEIGHT - 1)));
        }
        for (int r = 1; r < Constants.HEIGHT - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(Constants.WIDTH - 1, r)));
        }
        return walls;
    }

    @Override
    protected List<Brick> createBricks(){
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

    @Override
    protected Points createPoints(){
        return new Points();
    }
}
