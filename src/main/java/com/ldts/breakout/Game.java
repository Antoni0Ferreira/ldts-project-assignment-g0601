package com.ldts.breakout;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.gui.KeyBoardObserver;
import com.ldts.breakout.gui.LanternaGUI;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.MenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private int fps;
    private GameState gameState;
    private GUI gui;
    private KeyBoardObserver keyBoardObserver;

    private static Game singleton = null;

    public Game(int fps) throws IOException,FontFormatException{
        this.gui = new LanternaGUI();
        this.fps = fps;
        this.gameState = new MenuState(this, gui);
        this.keyBoardObserver = new KeyBoardObserver();
    }

    public void start() throws IOException{
        int frameTime = 1000 / this.fps;

        gui.addKeyBoardListener(keyBoardObserver);
        this.gameState.start();
        while (gameState != null){
            long startTime = System.currentTimeMillis();

            gameState.step(this);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
        }

        gui.close();
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

    public static Game getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (singleton == null) {
            singleton = new Game(15);
        }
        return singleton;
    }
}
