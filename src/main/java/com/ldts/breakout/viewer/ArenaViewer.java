package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Element;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.model.Brick;

import java.io.IOException;
import java.util.List;

public class ArenaViewer {
    private final GUI gui;
    private final Arena arena;

    public ArenaViewer(GUI gui, Arena arena){
        this.gui = gui;
        this.arena = arena;
    }

    public GUI getGui() {
        return gui;
    }

    public void draw() throws IOException{
        gui.clear();
        drawBackground();
        drawElement(this.arena.getBall(),new BallViewer());
        drawElement(this.arena.getPaddle(),new PaddleViewer());
        drawElement(this.arena.getPoints(), new PointsViewer());
        drawElements(this.arena.getWalls(), new WallViewer());
        drawBricks(this.arena.getBricks(), new BrickViewer());

        gui.refresh();
    }

    private void drawBackground(){};

    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer){
        for(T element: elements)
            drawElement(element,viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer){
        viewer.drawElement(element,gui);
    }

    private void drawBricks(List<Brick> elements, ElementViewer<Brick> viewer){
        for(Brick element: elements)
            if(!element.isDestroyed())
                drawElement(element,viewer);
    }
    public GUI getGui(){
        return gui;
    }
}
