import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.apache.groovy.json.internal.IO;

import java.io.IOException;
import java.security.Key;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game(){

        try{
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(Constants.WIDTH, Constants.HEIGHT)).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();


        }catch(IOException e){
            e.printStackTrace();
        }

        arena = new Arena();

    }

    public void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) {arena.processKey(key);}

    public void run(){
        try {
            while(true) {
                draw();
                com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                processKey(key);

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;

            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
