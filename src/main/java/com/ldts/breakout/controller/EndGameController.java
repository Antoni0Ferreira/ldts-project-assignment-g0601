package com.ldts.breakout.controller;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.KeyBoardListener;
import com.ldts.breakout.viewer.state.EndGameViewer;

import java.io.IOException;

public class EndGameController implements KeyBoardListener {
    private final GameState gameState;
    private final EndGameViewer endGameViewer;
    private boolean won;

    public EndGameController(GameState gameState, GUI gui, boolean gameWon) {
        this.gameState = gameState;
        this.won = gameWon;
        this.endGameViewer = new EndGameViewer(gui, gameState.getButtons(), gameWon);
    }

    public void step() throws IOException {
        endGameViewer.draw();
    }

    private int getActiveButton(){
        for(int i = 0; i < gameState.getButtons().size(); i++){
            if(gameState.getButtons().get(i).isActive()) return i;
        }
        return -1;
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT){
            gameState.changeState(null);
        }

        if(action == GUI.ACTION.CHOOSE){
            int index = getActiveButton();
            if(index != -1)
                gameState.getButtons().get(index).getCommand().execute();
        }
    }
}


