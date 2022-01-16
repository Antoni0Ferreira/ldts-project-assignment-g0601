package com.ldts.breakout.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.ldts.breakout.Constants;
import com.ldts.breakout.model.Menu;
import com.ldts.breakout.model.MenuOption;
import com.ldts.breakout.model.Position;
import org.w3c.dom.Text;
import java.util.concurrent.TimeUnit;
import static com.googlecode.lanterna.Symbols.HEART;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.googlecode.lanterna.Symbols.FACE_BLACK;
import static com.googlecode.lanterna.Symbols.SOLID_SQUARE;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;

    public LanternaGUI() throws IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadFont();
        Terminal terminal = createTerminal(fontConfig);
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

    public Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException{
        TerminalSize terminalSize = new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
        /*DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        defaultTerminalFactory.setForceAWTOverSwing(true);
        defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();*/
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        defaultTerminalFactory.setForceAWTOverSwing(true);
        defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = defaultTerminalFactory.createTerminal();

        return terminal;
    }

    public AWTTerminalFontConfiguration loadFont() throws FontFormatException,IOException{
        File fontFile = new File("..\\BreakoutGame\\resources\\PressStart2P.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 15);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public TerminalSize getSize(){
        return new TerminalSize(Constants.WIDTH,Constants.HEIGHT);
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
        textGraphics.fillRectangle(new TerminalPosition(0,0),
                new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
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
    public void addKeyBoardListener(KeyBoardObserver observer){
        ((AWTTerminalFrame) screen.getTerminal()).getComponent(0).addKeyListener(observer);
    }

    @Override
    public void drawBall(Position position){
        TextGraphics textGraphics = createTextGraphics();
        drawText(textGraphics,position,"@","#FFFFFF");
/*        if (lostLife){
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
/*        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.setCharacter(new TerminalPosition(position.getX(), position.getY()),FACE_BLACK);*/
    }

    @Override
    public void drawPaddle(Position position){
        TextGraphics textGraphics = createTextGraphics();
        drawRectangle(textGraphics,position,'-',"#FF7000",Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT);
/*        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF7000"));
        textGraphics.drawRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);*/
    }

    @Override
    public void drawPoints(int numPoints, Position position){
        //TextGraphics textGraphics = createTextGraphics();
        //drawText(textGraphics,position,String.valueOf(numPoints),"#FF00FF");
/*        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), String.valueOf(numPoints));*/
    }

    @Override
    public void drawWall(Position position){
        TextGraphics textGraphics = createTextGraphics();
        String color;
        switch (position.getY()){
            case 4 -> color = "#FF0000";
            case 5 -> color = "#FF8700";
            case 6 -> color = "#FFC100";
            case 7 -> color = "#CDFF00";
            case 8 -> color = "#008FFF";
            case Constants.INIT_PADDLE_Y -> color = "#FF7000";
            default -> color = "#FFFFFF";
        }
        //textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        drawText(textGraphics,position," ",color);
    }

    @Override
    public void drawBrick(int points, Position position){

        TextGraphics textGraphics = createTextGraphics();
        String color = "";
        switch (points){
            case Constants.RED_BRICK -> color = "#FF0000";
            case Constants.DARK_ORANGE_BRICK -> color = "#FF8700";
            case Constants.LIGHT_ORANGE_BRICK -> color = "#FFC100";
            case Constants.YELLOW_BRICK -> color = "#CDFF00";
            case Constants.BLUE_BRICK -> color = "#008FFF";
        }
        drawRectangle(textGraphics,position,'-',color,Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT);
/*        textGraphics.drawRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),SOLID_SQUARE);*/
    }

    @Override
    public void drawMenuOption(char option, Position position){
        TextGraphics textGraphics = createTextGraphics();
        switch (option){
            case 's':{
                textGraphics.setForegroundColor(TextColor.Factory.fromString("#CDFF00"));
                textGraphics.putString(new TerminalPosition(position.getX(),position.getY()),"START");
                break;
            }
            case 'i':{
                textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFC100"));
                textGraphics.putString(new TerminalPosition(position.getX(),position.getY()),"INSTRUCTIONS");
                break;
            }
            case 'e':{
                textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
                textGraphics.putString(new TerminalPosition(position.getX(),position.getY()),"EXIT");
                break;
            }
        }
    }

/*    @Override
    public void drawString(String color, int row, String s){
        TextGraphics textGraphics = createTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.putString(Constants.WIDTH - s.length() / 2,row,s);
    }

    @Override
    public int getMenuOptionLine(Menu.Option option){
        if(option == Menu.Option.START) return 15;
        else if (option == Menu.Option.INST) return 20;
        else return 25;

    }

    @Override
    public void drawMenu(Menu.Option option, Position position){
        TextGraphics textGraphics = createTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), getSize(), ' ');

        drawString("#00FF00", 15, "START"); //green
        drawString("#f2e744", 20, "INSTRUCTIONS");   //yellow
        drawString("#FF0000", 25, "QUIT");

        Menu.Option selected = option;
        int col_begin_selected = this.getSize().getColumns() / 2 -3 ;
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(col_begin_selected,getMenuOptionLine(option),"&'"); //ja esta atualizado para a fonte nova
    }*/

    public void drawText(TextGraphics textGraphics, Position position, String text, String color){
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(position.getX(),position.getY(),text);
    }

    public void drawRectangle(TextGraphics textGraphics, Position position, char character, String color,int width, int height){
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.drawRectangle(new TerminalPosition(position.getX(), position.getY()),
                new TerminalSize(width,height),character);
    }

    @Override
    public void drawButton(Position bPos, Position tPos, String text, String textColor){
        TextGraphics textGraphics=screen.newTextGraphics();
        drawText(textGraphics, tPos, text, textColor);
    }

    @Override
    public void drawTitle(Position position, String text, String color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        drawText(textGraphics, position, text,color);
    }

    @Override
    public void drawInfo(int points, int lives){
        TextGraphics textGraphics = screen.newTextGraphics();
        drawText(textGraphics, new Position(3, 37), Integer.toString(points), "#FF00FF");
        drawText(textGraphics, new Position(53, 37), Integer.toString(lives) + HEART, "#FF0000");
    }
}
