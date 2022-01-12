package com.ldts.breakout.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.controller.MenuController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Menu;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuState extends GameState {
    private MenuController menuController;

    public Menustate(Game game, GUI gui) throws IOException {
        List<Button> buttons = Arrays.asList();
        Button playButton = new Button(new Position(Constants.INIT_BALL_X, 15), "PLAY", new MenuButtonCommand(PlayingState(game,gui)));
        Button instButton = new Button(new Position(Constants.INIT_BALL_X, 20), "INSTRUCTIONS", new MenuButtonCommand(InstructionsState(game, gui)));
        Button quitButton = new Button(new Position(Constants.INIT_BALL_X, 25), "QUIT", new MenuButtonCommand(null));
    }

    @Override
    public void start() {
        game.getKeyboardObserver().setListener(menuController);
    }

    @Override
    public void step(Game game, long time) throws IOException {

    }
}
