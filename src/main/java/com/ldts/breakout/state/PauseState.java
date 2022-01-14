package com.ldts.breakout.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.controller.PauseController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;

public class PauseState extends GameState {
    private PauseController pauseController;

    public PauseState(Game game, GUI gui, GameState previousState) throws IOException {
        super(game, Arrays.asList(
                new Button(new Position(Constants.INIT_BALL_X - 3, 17),"RESUME", new MenuButtonCommand(previousState), "#FFFFFF"),
                new Button(new Position(Constants.INIT_BALL_X - 2, 21), "EXIT", new MenuButtonCommand(new MenuState(game,gui)), "#FFFFFF")
        ));
        pauseController = new PauseController(this, gui);
        getButtons().get(0).activate();
    }

    @Override
    public void start() {
        game.getKeyBoardObserver().setListener(pauseController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        this.pauseController.step();
    }

    public PauseController getPauseController() {
        return pauseController;
    }

    public void setPauseController(PauseController pauseController) {
        this.pauseController = pauseController;
    }
}
