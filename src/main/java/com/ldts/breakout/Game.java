package com.ldts.breakout;


import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.MenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;

public class Game {
    private GameState gameState;

    public Game(){
        this.gameState = new MenuState(this, Arrays.asList());
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
