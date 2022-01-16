package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Ball;

public class BallViewer implements ElementViewer<Ball>{
    @Override
    public void drawElement(Ball element, GUI gui){
        gui.drawBall(element.getPosition());
    }
}
