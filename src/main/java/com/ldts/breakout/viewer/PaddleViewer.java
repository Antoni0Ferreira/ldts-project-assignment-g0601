package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Paddle;

public class PaddleViewer implements ElementViewer<Paddle>{
    @Override
    public void drawElement(Paddle element, GUI gui){
        gui.drawPaddle(element.getPosition());
    }
}