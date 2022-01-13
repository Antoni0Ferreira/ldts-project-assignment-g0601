package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.controller.PlayingController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.model.arena.ArenaBuilder;
import com.ldts.breakout.model.arena.ArenaLoader;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class PlayingState extends GameState {
    private PlayingController playingController;
    private Arena arena;

    public PlayingState(Game game, GUI gui) throws IOException{
        super(game, Arrays.asList());

        gui.refresh();
        gui.clear();
        this.arena = new ArenaLoader().createArena();
        this.playingController = new PlayingController(this,gui,arena);
    }

    @Override
    public void start(){ game.getKeyBoardObserver().setListener(playingController);}
    @Override
    public void step(Game game, long time) throws IOException{
        playingController.step(game, time);
    }
}
