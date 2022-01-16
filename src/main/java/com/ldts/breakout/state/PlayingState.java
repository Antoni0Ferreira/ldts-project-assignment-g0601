package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.model.Button;
import java.util.List;

import java.util.Arrays;

public class PlayingState extends GameState{

    public PlayingState(Game game, List<Button> list){
        super(game, list);
    }

    @Override
    public void start(){};
}
