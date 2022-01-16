package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.model.Button;
import java.util.List;

public class MenuState extends GameState{

    public MenuState(Game game, List<Button> list){
        super(game, list);
    }

    @Override
    public void start(){};

}
