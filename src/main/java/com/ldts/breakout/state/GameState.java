package com.ldts.breakout.state;

import com.ldts.breakout.Game;
import com.ldts.breakout.model.Button;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public abstract class GameState {
    protected final Game game;
    private final List<Button> buttons;

    public GameState(Game game, List<Button> buttons) {
        this.game = game;
        this.buttons = buttons;
    }

    protected GameState() {
    }

    public void changeState(GameState gameState){this.game.setGameState(gameState);}

    public Game getGame(){return game;}

    public List<Button> getButtons(){return buttons;}

    //public abstract void start();

    //public abstract void step(Game game, long time) throws IOException;
}
