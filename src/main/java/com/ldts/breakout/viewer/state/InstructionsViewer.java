package com.ldts.breakout.viewer.state;

import com.ldts.breakout.controller.InstructionsController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;

import java.io.IOException;
import java.util.List;

public class InstructionsViewer extends StateViewer{

    public InstructionsViewer(GUI gui, List<Button> buttons){super(gui, buttons);}

    @Override
    public void draw() throws IOException{
        gui.clear();
        drawText(new Position(1,12),"2.AIM/SHOOT WITH MOUSE", "#FFFFFF");
    }

    private void drawText(Position position, String text, String textColor){
        gui.drawTitle(position,text,textColor);
    }
}
