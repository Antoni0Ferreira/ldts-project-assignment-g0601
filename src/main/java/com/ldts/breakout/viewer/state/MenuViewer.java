package com.ldts.breakout.viewer.state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.ldts.breakout.Constants;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Menu;
import com.ldts.breakout.model.MenuOption;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.viewer.state.StateViewer;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.viewer.ButtonViewer;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MenuViewer extends StateViewer {

    public MenuViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException, FontFormatException {
        gui.clear();
        drawBackground();
        drawButtons(buttons, new ButtonViewer());
        gui.drawTitle(new Position(Constants.INIT_BALL_X - 12, getYActiveButton()), "&'", "#FF0000");
        gui.drawTitle(new Position(15, 4), "BREAKOUT", "#FFFFFF");
        gui.refresh();
    }
}
