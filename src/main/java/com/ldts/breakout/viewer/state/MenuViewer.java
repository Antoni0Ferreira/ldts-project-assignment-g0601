package com.ldts.breakout.viewer.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.viewer.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends StateViewer {

    public MenuViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        drawBackground();
        drawButtons(buttons, new ButtonViewer());
        gui.drawTitle(new Position(Constants.INIT_BALL_X - 12, getYActiveButton()), "&'", "#FF0000");
        gui.drawTitle(new Position(Constants.INIT_BALL_X - 5, 13), "MAIN MENU:", "#FF0000");
        gui.refresh();
    }
}
