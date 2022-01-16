package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Wall;

public class WallViewer implements ElementViewer<Wall>{
    @Override
    public void drawElement(Wall element, GUI gui) {
        gui.drawWall(element.getPosition());
    }
}
