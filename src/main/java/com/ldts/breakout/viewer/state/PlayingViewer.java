package com.ldts.breakout.viewer.state;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Paddle;

import java.io.IOException;
import java.util.List;

public class PlayingViewer extends StateViewer {
    private Paddle paddle;

    public PlayingViewer(GUI gui, List<Button> buttons,Paddle paddle){
        super(gui, buttons);
        this.paddle = paddle;
    }

    @Override
    public void draw() throws IOException{
        gui.drawInfo(paddle.getPoints(),paddle.getLives());
        gui.refresh();
    }
}