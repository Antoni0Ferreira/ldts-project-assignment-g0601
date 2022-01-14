package com.ldts.breakout.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.controller.MenuController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Menu;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.command.MenuButtonCommand;
import com.ldts.breakout.viewer.state.MenuViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuState extends GameState{
    private MenuController menuController;

    public MenuState(Game game, GUI gui) throws IOException{
        super(game,Arrays.asList(
                new Button(new Position(Constants.INIT_BALL_X - 2, 15), "PLAY", new MenuButtonCommand(new PlayingState(game,gui)),"#FFFFFF"),
                new Button(new Position(Constants.INIT_BALL_X - 6, 20), "INSTRUCTIONS", new MenuButtonCommand(new InstructionsState(game, gui)),"#FFFFFF"),
                new Button(new Position(Constants.INIT_BALL_X - 2, 25), "QUIT", new MenuButtonCommand(null),"#FFFFFF")

        ));
       this.menuController = new MenuController(this,gui);
       getButtons().get(0).activate();
    }

    @Override
    public void start() {
        game.getKeyBoardObserver().setListener(menuController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        this.menuController.step();
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
