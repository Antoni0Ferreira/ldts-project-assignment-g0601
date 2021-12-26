import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import static com.googlecode.lanterna.Symbols.FACE_BLACK;
import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;
import static com.googlecode.lanterna.Symbols.BLOCK_SOLID;

import java.util.ArrayList;
import java.util.List;

public class Wall extends Element {
    private List<Wall> walls;


    public Wall(int x, int y) {
        super(x,y);
    }

    public Position getPosition() {
        return  position;
    }
    public void setPosition(Position position){
        this.position = position;
    }

    public void draw(TextGraphics graphics) {
       switch (getPosition().getY()){
           case 4 -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
           case 5 -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FF8700"));
           case 6 -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FFC100"));
           case 7 -> graphics.setBackgroundColor(TextColor.Factory.fromString("#CDFF00"));
           case 8 -> graphics.setBackgroundColor(TextColor.Factory.fromString("#008FFF"));
           case Constants.INIT_PADDLE_Y -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FF7000"));
           default -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        }
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");
    }
}