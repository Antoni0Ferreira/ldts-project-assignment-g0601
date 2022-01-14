package com.ldts.breakout.model.arena;

import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.model.*;

import java.util.List;

public abstract class ArenaBuilder {

    public Arena createArena(){
        Arena arena = new Arena();
        arena.setBall(createBall());
        arena.setPaddle(createPaddle());
        arena.setWalls(createWalls());
        arena.setBricks(createBricks());
        arena.setPoints(createPoints());
        return arena;
    }

    protected abstract Ball createBall();

    protected abstract Paddle createPaddle();

    protected abstract Points createPoints();

    protected abstract List<Brick> createBricks();

    protected abstract List<Wall> createWalls();

}
