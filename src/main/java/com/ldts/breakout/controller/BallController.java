package com.ldts.breakout.controller;

import com.ldts.breakout.Game;
import com.ldts.breakout.model.arena.Arena;

import java.io.IOException;

public class BallController {
    private  ArenaController arenaController;
    private long lastMovement;
    private long beginningGameTime;
/*
    public BallController(Arena arena, ArenaController arenaController){
        super(arena);

        this.arenaController = arenaController;
        this.lastMovement = 0;
        this.beginningGameTime = 0;
    }*/

/*    public class BallThread extends Thread{
        @Override
        public void run(){

            try{
                sleep(1000);
                while(!stopThread){
                    sleep(50);
                    draw();
                    arena.hitsPaddle();

                    if(!arena.getBall().getDestroyedBrick())
                        arena.hitsBrick();

                    screen.clear();
                    if(arena.gameLost()){gameEnded(false);}
                    if(arena.gameWon()){gameEnded(true);}
                    arena.getBall().move();
                }
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
