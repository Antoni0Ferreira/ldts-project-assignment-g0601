package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Brick;

public class BrickViewer implements ElementViewer<Brick> {
    @Override
    public void drawElement(Brick element, GUI gui){
        gui.drawBrick(element.getPoints(),element.getPosition());
    }
}
