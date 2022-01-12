package com.ldts.breakout.controller;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.gui.KeyBoardObserver;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.KeyBoardListener;
import com.ldts.breakout.viewer.state.InstructionsViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.io.IOException;
import java.util.ArrayList;

public class InstructionsController implements KeyBoardListener {
    private final GameState gameState;
    private final StateViewer instructionsViewer;
    
    public InstructionsController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.instructionsViewer = new InstructionsViewer(gui,new ArrayList<>());
    }
    
    public void step() throws IOException {
        instructionsViewer.draw();
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT){
            gameState.changeState(null);
        }
    }
}
