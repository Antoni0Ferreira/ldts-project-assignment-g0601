package com.ldts.breakout.controller;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.KeyBoardListener;
import com.ldts.breakout.viewer.state.EndGameViewer;
import com.ldts.breakout.viewer.state.PlayingViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.io.IOException;
import java.util.ArrayList;


public class PlayingController extends GameController implements KeyBoardListener {
    private final GameState gameState;
    private ArenaController arenaController;
    private final GUI gui;
    private final StateViewer playingViewer;
    private long endTime;

    public PlayingController(GameState gameState, GUI gui, Arena arena){
        super(arena);
        this.gameState = gameState;
        this.gui = gui;
        this.arenaController = new ArenaController(gameState,gui,arena);
        this.playingViewer = new PlayingViewer(gui,new ArrayList<>(), arena.getPaddle());
    }

    private void changeState(GameState gameState){this.gameState.changeState(gameState);}

    @Override
    public void step(Game game, long time) throws IOException {
        arenaController.step(game,time);
        playingViewer.draw();
        if(arenaController.getModel().getPaddle().getLives() == 0){
            EndGameViewer endGameViewer = new EndGameViewer(gui,null,false);
            endGameViewer.draw();
        }
        else if(arenaController.getModel().getPaddle().getPoints() == Constants.MAX_POINTS){
            EndGameViewer endGameViewer = new EndGameViewer(gui,null,true);
            endGameViewer.draw();
        }

    }
    
    @Override
    public void keyPressed(GUI.ACTION action){
        arenaController.doAction(action);
    }

    public void checkButtons() {
        for(Button button: gameState.getButtons()) {
            if(!button.isActive()) continue;

            if (!button.getCommand().execute()) button.deactivate();
        }
    }
    
    public ArenaController getArenaController() {return arenaController;}
    
    public void setupModel(Arena arena){
        this.arenaController = new ArenaController(gameState,gui,arena);
        this.setModel(arena);
    }

}
