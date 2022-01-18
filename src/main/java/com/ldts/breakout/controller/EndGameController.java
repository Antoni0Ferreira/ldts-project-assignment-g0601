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

    @Override
    public void keyPressed(GUI.ACTION action) throws IOException {

    }
}


