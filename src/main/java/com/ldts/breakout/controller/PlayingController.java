package com.ldts.breakout.controller;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.state.*;
import com.ldts.breakout.viewer.state.PlayingViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.io.IOException;
import java.util.ArrayList;


public class PlayingController extends GameController implements KeyBoardListener {
    private final GameState gameState;
    private ArenaController arenaController;
    private final GUI gui;
    private final StateViewer playingViewer;
    private boolean initial = true;

    public PlayingController(GameState gameState, GUI gui, Arena arena) {
        super(arena);
        this.gameState = gameState;
        this.gui = gui;
        this.arenaController = new ArenaController(gameState, gui, arena);
        this.playingViewer = new PlayingViewer(gui, new ArrayList<>(), arena.getPaddle());
    }

    private void changeState(GameState gameState) {
        this.gameState.changeState(gameState);
    }

    @Override
    public void step(Game game) throws IOException {
        if (initial) {
            arenaController.getBallController().startBallTimer();
        }
        arenaController.step(game);
        playingViewer.draw();

        if (getModel().getPaddle().getLives() == 0) {
            changeState(new EndGameState(this.gameState.getGame(), gui, false));
        } else if (getModel().getPaddle().getPoints() == Constants.MAX_POINTS) {
            changeState(new EndGameState(this.gameState.getGame(), gui, true));
        }
        this.initial = false;
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if (action == GUI.ACTION.QUIT) {
            try {
                changeState(new PauseState(gameState.getGame(), this.gui, this.gameState));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        arenaController.doAction(action);
    }
}