package com.ldts.breakout;

public class Brick extends Element {

    private boolean destroyed;
    private int points;

    public Brick(Position position) {
        super(position);
        switch (getPosition().getY()) {
            case 4 -> points = Constants.RED_BRICK;
            case 5 -> points = Constants.DARK_ORANGE_BRICK;
            case 6 -> points = Constants.LIGHT_ORANGE_BRICK;
            case 7 -> points = Constants.YELLOW_BRICK;
            case 8 -> points = Constants.BLUE_BRICK;
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}