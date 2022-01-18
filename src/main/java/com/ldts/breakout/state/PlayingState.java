
package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.controller.PlayingController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.model.arena.ArenaBuilder;

import java.io.IOException;
import java.util.Arrays;

public class PlayingState extends GameState {
    private PlayingController playingController;
    private Arena arena;

    public PlayingState(Game game, GUI gui) throws IOException{
        super(game, Arrays.asList());

        gui.refresh();
        gui.clear();
        this.arena = new ArenaBuilder().createArena();
        this.playingController = new PlayingController(this,gui,arena);
    }

    @Override
    public void start(){ game.getKeyBoardObserver().setListener(playingController);}


    @Override
    public void step(Game game) throws IOException{
        playingController.step(game);
    }

    public PlayingController getPlayingController() {return playingController;}

    public void setPlayingController(PlayingController playingController) {this.playingController = playingController;}
}