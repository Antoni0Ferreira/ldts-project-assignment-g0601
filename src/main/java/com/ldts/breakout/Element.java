package com.ldts.breakout;

public abstract class Element {
    protected Position position;

    public Element(int x, int y){
        position = new Position(x,y);
    }

    public Element(Position position) { this.position = position; }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

}