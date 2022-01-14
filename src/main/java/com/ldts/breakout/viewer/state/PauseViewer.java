package com.ldts.breakout.viewer.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.viewer.ButtonViewer;
import com.ldts.breakout.viewer.state.StateViewer;

import java.io.IOException;
import java.util.List;

public class PauseViewer extends StateViewer {

    public PauseViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    public void draw() throws IOException {
        gui.clear();
        drawBackground();
        drawButtons(buttons, new ButtonViewer());
        gui.drawTitle(new Position(Constants.INIT_BALL_X -5, 13), "PAUSE MENU", "#FF0000");
        gui.drawTitle(new Position(Constants.INIT_BALL_X - 7, getYActiveButton()), "&'", "#FF0000");
        gui.refresh();
    }

}