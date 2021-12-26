import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;

public class Paddle extends Element{

    public Paddle(int x, int y) {
        super(x, y);
    }

    public Position moveLeft(){
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }
    public Position moveRight(){
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF7000"));
        graphics.drawRectangle(new TerminalPosition(getPosition().getX(), getPosition().getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);
    }
}
