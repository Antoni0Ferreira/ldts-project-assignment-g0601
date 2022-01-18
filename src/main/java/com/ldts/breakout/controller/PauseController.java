package com.ldts.breakout.controller;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.KeyBoardListener;
import com.ldts.breakout.viewer.state.PauseViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.awt.event.MouseListener;
import java.io.IOException;

public class PauseController implements KeyBoardListener {
    private final GameState gameState;
    private final StateViewer pauseViewer;

    public PauseController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.pauseViewer = new PauseViewer(gui, gameState.getButtons());
    }

    public void step() throws IOException {
        pauseViewer.draw();
    }

    private int getActiveButton(){
        for(int i = 0; i < gameState.getButtons().size(); i++){
            if(gameState.getButtons().get(i).isActive()) return i;
        }
        return -1;
    }


    @Override
    public void keyPressed(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT || (gameState.getButtons().get(getActiveButton()).getPosition().getY() == 20 && action == GUI.ACTION.CHOOSE)){
            gameState.changeState(null);
        }

        if(action == GUI.ACTION.UP){
            int index = getActiveButton();
            if(index > 0){
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(index - 1).activate();
            }
            else{
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(gameState.getButtons().size()-1).activate();
            }
        }

        if(action == GUI.ACTION.DOWN){
            int index = getActiveButton();
            if(index < gameState.getButtons().size() -1){
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(index + 1).activate();
            }
            else{
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(0).activate();
            }
        }

        if(action == GUI.ACTION.CHOOSE){
            int index = getActiveButton();
            if(index != -1)
                gameState.getButtons().get(index).getCommand().execute();
        }
    }

    public GameState getGameState() {return gameState;}
}