package com.ldts.breakout.model.command;

import com.ldts.breakout.state.GameState;

public class MenuButtonCommand implements Command{
    private final GameState nextState;

    public MenuButtonCommand(GameState nextState){this.nextState = nextState;}

    @Override
    public boolean execute(){
        if(nextState != null)
            nextState.changeState(nextState);
        return true;

    }

    @Override
    public void undo() {nextState.changeState(nextState);}

    public GameState getNextState() {return nextState;}
}
