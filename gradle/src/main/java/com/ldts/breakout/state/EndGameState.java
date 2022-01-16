package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.controller.EndGameController;
import com.ldts.breakout.controller.InstructionsController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;

public class EndGameState extends GameState{
    private EndGameController endGameController;
    private boolean won;

    public EndGameState(Game game, GUI gui, boolean won) throws IOException {
        super(game, Arrays.asList(new Button(new Position(4,35),"GO BACK TO THE MAIN MENU (PRESS ENTER)",
                new MenuButtonCommand(new MenuState(game,gui)),"#FFFFFF")));
        this.endGameController = new EndGameController(this,gui,won);
        getButtons().get(0).activate();
    }

    @Override
    public void start(){game.getKeyBoardObserver().setListener(endGameController);}

    @Override
    public void step(Game game, long time) throws IOException{
        endGameController.step();
    }

    public  EndGameController getEndGameController() {
        return endGameController;
    }

    public void setEndGameController(InstructionsController instructionsController) {
        this.endGameController = endGameController;
    }
}
