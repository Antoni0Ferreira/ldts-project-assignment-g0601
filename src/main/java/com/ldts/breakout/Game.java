package com.ldts.breakout;



import com.ldts.breakout.state.GameState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;

public class Game {
    private GameState gameState;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }
}
