package com.ldts.breakout.model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.breakout.Constants;
import com.ldts.breakout.model.Element;

public class Points extends Element {
    private int numPoints;

    public Points() {
        super(Constants.POINTS_X,Constants.POINTS_Y);
        numPoints=0;
    }

    public void setNumPoints(int numPoints){this.numPoints = numPoints;}
    public int getNumPoints() {return numPoints;}

    public void add(int points) {
        numPoints += points;
    }
}