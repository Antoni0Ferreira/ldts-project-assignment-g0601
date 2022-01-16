package com.ldts.breakout;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.gui.KeyBoardObserver;
import com.ldts.breakout.gui.LanternaGUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.MenuState;

import java.awt.*;
import java.io.IOException;

public class Game {
    private GameState gameState;
    private GUI gui;
    private KeyBoardObserver keyBoardObserver;

    public Game() throws IOException,FontFormatException{
        this.gui = new LanternaGUI();
        this.gameState = new MenuState(this, gui);
        this.keyBoardObserver = new KeyBoardObserver();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }

    public GameState getGameState(){
        return gameState;
    }

    public KeyBoardObserver getKeyBoardObserver() {
        return keyBoardObserver;
    }
}
