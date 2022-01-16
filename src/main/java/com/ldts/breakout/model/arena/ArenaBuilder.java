package com.ldts.breakout.model.arena;

import com.ldts.breakout.*;
import com.ldts.breakout.model.*;

import java.util.ArrayList;
import java.util.List;

public class ArenaBuilder {

    public Arena createArena(){
        Arena arena = new Arena();
        arena.setBall(createBall());
        arena.setPaddle(createPaddle());
        arena.setWalls(createWalls());
        arena.setBricks(createBricks());
        return arena;
    }

    public Ball createBall(){
        return new Ball();
    }

    public Paddle createPaddle(){
        return new Paddle();
    }

    public List<Wall> createWalls(){
        ArrayList<Wall> walls = new ArrayList <>();
        for (int c = 0; c < Constants.WIDTH+1; c++) {
            walls.add(new Wall(new Position(c,0)));
            walls.add(new Wall(new Position(c, Constants.HEIGHT-1)));
        }
        for (int r = 1; r < Constants.HEIGHT ; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(Constants.WIDTH-1, r)));
        }
        return walls;
    }


    public List<Brick> createBricks(){
        ArrayList<Brick> bricks = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            int count = 0;
            for(int j = 0; j < 7; j++){
                bricks.add(new Brick(new Position(j + 1 + count ,i + 4)));
                count += 7;
            }
        }
        return bricks;
    }


}
