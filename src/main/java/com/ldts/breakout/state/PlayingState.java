package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.gui.GUI;

import java.util.Arrays;

public class PlayingState extends GameState{

    public PlayingState(Game game, GUI gui){
        super(game, Arrays.asList());
    }

    @Override
    public void start(){};
}
