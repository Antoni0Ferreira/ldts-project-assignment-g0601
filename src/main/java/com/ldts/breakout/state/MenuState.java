package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.gui.GUI;

import java.util.Arrays;

public class MenuState extends GameState{

    public MenuState(Game game, GUI gui){
        super(game, Arrays.asList());
    }

    @Override
    public void start(){};

    @Override
    public void step(Game game){}


}
