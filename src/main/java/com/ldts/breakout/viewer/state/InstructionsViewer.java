package com.ldts.breakout.viewer.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.controller.InstructionsController;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.viewer.ButtonViewer;

import static com.googlecode.lanterna.Symbols.HEART;

import java.io.IOException;
import java.util.List;

public class InstructionsViewer extends StateViewer{

    public InstructionsViewer(GUI gui, List<Button> buttons){super(gui, buttons);}

    @Override
    public void draw() throws IOException{
        gui.clear();
        drawBackground();
        drawButtons(buttons, new ButtonViewer());
        gui.drawTitle(new Position(1, getYActiveButton()), "&'", "#FF0000");
        drawText(new Position(1,4),"HOW TO PLAY:", "#FF0000");
        drawText(new Position(1,8),"1.", "#FF8700");
        drawText(new Position(4,8),"Move the paddle with the { and } keys", "#FFFFFF");
        drawText(new Position(1,10),"2.", "#FF8700");
        drawText(new Position(4,10),"Try to hit the ball to destroy all of the bricks", "#FFFFFF");
        drawText(new Position(1,12),"3.", "#FF8700");
        drawText(new Position(4,12),"Depending on the color, bricks are worth a", "#FFFFFF");
        drawText(new Position(1,14),"different amount of points:", "#FFFFFF");
        drawText(new Position(1,16),"--", "#FF0000"); drawText(new Position(4,16),"50 points", "#FFFFFF");
        drawText(new Position(1,18),"--", "#FF8700"); drawText(new Position(4,18),"20 points", "#FFFFFF");
        drawText(new Position(1,20),"--", "#FFC100"); drawText(new Position(4,20),"10 points", "#FFFFFF");
        drawText(new Position(1,22),"--", "#CDFF00"); drawText(new Position(4,22),"5 points", "#FFFFFF");
        drawText(new Position(1,24),"--", "#008FFF"); drawText(new Position(4,24),"1 point", "#FFFFFF");
        drawText(new Position(1,26),"4.", "#FF8700");
        drawText(new Position(4,26),"You only have 3 lives (", "#FFFFFF");
        drawText(new Position(27,26),Character.toString(HEART),"#FF0000"); drawText(new Position(28,26),")","#FFFFFF");
        drawText(new Position(1,28),"5.", "#FF8700");
        drawText(new Position(4,28),"The game ends when all the bricks are destroyed", "#FFFFFF");
        drawText(new Position(1,30),"or when you lose all of your 3 lives", "#FFFFFF");
        gui.refresh();
    }

    private void drawText(Position position, String text, String textColor){
        gui.drawTitle(position,text,textColor);
    }
}
