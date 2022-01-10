package com.ldts.breakout.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.ldts.breakout.Constants;
import com.ldts.breakout.Position;

import java.io.IOException;

import static com.googlecode.lanterna.Symbols.FACE_BLACK;
import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;

    public LanternaGUI() throws IOException {
        Terminal terminal = createTerminal();
        screen = createScreen(terminal);
    }

    public TerminalScreen createScreen(Terminal terminal) throws IOException{
        final TerminalScreen terminalScreen;
        terminalScreen = new TerminalScreen(terminal);
        terminalScreen.setCursorPosition(null);
        terminalScreen.startScreen();
        terminalScreen.doResizeIfNecessary();
        return terminalScreen;
    }

    public Terminal createTerminal() throws IOException{
        TerminalSize terminalSize = new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    @Override
    public TextGraphics createTextGraphics(){
        return screen.newTextGraphics();
    }

    @Override
    public int getWidth(){
        return Constants.WIDTH;
    }

    @Override
    public int getHeight(){
        return Constants.HEIGHT;
    }

    @Override
    public void drawBackground(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
    }

    @Override
    public void clear(){
        screen.clear();
    }

    @Override
    public void refresh() throws IOException{
        screen.refresh();
    }

    @Override
    public void close() throws IOException{
        screen.close();
    }

    @Override
    public void drawBall(Position position, TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.setCharacter(new TerminalPosition(position.getX(), position.getY()),FACE_BLACK);
    }

    @Override
    public void drawPaddle(Position position, TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF7000"));
        textGraphics.drawRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);
    }

    @Override
    public void drawPoints(int numPoints, Position position, TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), String.valueOf(numPoints));
    }

    @Override
    public void drawWall(Position position, TextGraphics textGraphics){
        switch (position.getY()){
            case 4 -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
            case 5 -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF8700"));
            case 6 -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FFC100"));
            case 7 -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#CDFF00"));
            case 8 -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#008FFF"));
            case Constants.INIT_PADDLE_Y -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF7000"));
            default -> textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        }
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");
    }

    @Override
    public void drawBrick(int points, Position position, TextGraphics textGraphics){
        switch (points){
            case Constants.RED_BRICK -> textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            case Constants.DARK_ORANGE_BRICK -> textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF8700"));
            case Constants.LIGHT_ORANGE_BRICK -> textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFC100"));
            case Constants.YELLOW_BRICK -> textGraphics.setForegroundColor(TextColor.Factory.fromString("#CDFF00"));
            case Constants.BLUE_BRICK -> textGraphics.setForegroundColor(TextColor.Factory.fromString("#008FFF"));
        }
        textGraphics.drawRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);
    }
}
