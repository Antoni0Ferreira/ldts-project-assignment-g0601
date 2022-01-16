package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Points;
import com.ldts.breakout.model.Wall;

public class PointsViewer implements ElementViewer<Points> {
    @Override
    public void drawElement(Points element, GUI gui) {
        gui.drawPoints(element.getNumPoints(), element.getPosition());
    }

}
