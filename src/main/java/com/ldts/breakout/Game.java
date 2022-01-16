package com.ldts.breakout;

<<<<<<< HEAD
=======

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.gui.LanternaGUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.MenuState;

>>>>>>> d6bfcd12418967e7ff780b4a7e0f615dab24e5bf
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;

public class Game {
    private GameState gameState;
    private GUI gui;
    
    public Game() throws IOException,FontFormatException{
        gui = new LanternaGUI();
        this.gameState = new MenuState(this, gui);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }

    public GameState getGameState(){
        return gameState;
    }
}
