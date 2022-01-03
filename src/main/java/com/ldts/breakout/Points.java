package com.ldts.breakout;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Points extends Element {
    private int numPoints;

    public Points() {
        super(Constants.POINTS_X,Constants.POINTS_Y);
        numPoints=0;
    }

    void setNumPoints(int numPoints){this.numPoints = numPoints;}
    int getNumPoints() {return numPoints;}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), String.valueOf(numPoints));
    }

    public void add(int points) {
        numPoints += points;
    }
}