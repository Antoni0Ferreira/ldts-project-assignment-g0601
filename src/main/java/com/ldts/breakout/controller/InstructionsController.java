package com.ldts.breakout.controller;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.KeyBoardListener;
import com.ldts.breakout.viewer.state.InstructionsViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.io.IOException;

public class InstructionsController implements KeyBoardListener {
    private final GameState gameState;
    private final StateViewer instructionsViewer;

    public InstructionsController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.instructionsViewer = new InstructionsViewer(gui,gameState.getButtons());
    }

    public void step() throws IOException {
        try {
            instructionsViewer.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
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