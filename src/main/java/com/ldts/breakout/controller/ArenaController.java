package com.ldts.breakout.controller;

import com.ldts.breakout.Game;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.viewer.ArenaViewer;

public class ArenaController extends GameController {
    private final GameState gameState;
    private final ArenaViewer arenaViewer;
    private BallController ballController;
    private PaddleController paddleController;

    public ArenaController(GameState gameState, GUI gui, Arena arena){
        super(arena);
        this.gameState = gameState;
        this.arenaViewer = new ArenaViewer(gui,arena);
        this.ballController = new BallController();
        this.paddleController = new PaddleController(arena.getPaddle());
    }
    
    public PaddleController getPaddleController(){return paddleController;}
    
    public void doAction(GUI.ACTION action){
        Position nextPosition = paddleController.doAction(action);
    }


}
