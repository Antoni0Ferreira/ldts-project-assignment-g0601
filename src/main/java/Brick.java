import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;

public class Brick extends Element {

    private boolean destroyed;
    private int points;

    public Brick(int x, int y) {
        super(x, y);
        switch (getPosition().getY()) {
            case 4 -> points = 50;
            case 5 -> points = 20;
            case 6 -> points = 10;
            case 7 -> points = 5;
            case 8 -> points = 1;
        }
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    @Override
    public void draw(TextGraphics graphics) {

        switch (points){
            case 50 -> graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            case 20 -> graphics.setForegroundColor(TextColor.Factory.fromString("#FF8700"));
            case 10 -> graphics.setForegroundColor(TextColor.Factory.fromString("#FFC100"));
            case 5 -> graphics.setForegroundColor(TextColor.Factory.fromString("#CDFF00"));
            case 1 -> graphics.setForegroundColor(TextColor.Factory.fromString("#008FFF"));
        }
        graphics.drawRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);
    }
}
