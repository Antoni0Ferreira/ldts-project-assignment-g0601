package com.ldts.breakout;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.ldts.breakout.state.GameState;

import java.io.IOException;

import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;

public class Game {
    private Screen screen;
    private final OtherArena arena;
    private GameState gameState;
    private boolean stopThread = false;
    private boolean endGame = false;

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

        arena = new OtherArena();

    }

    // Only for tests
    public Game(OtherArena otherArena){
        this.arena = otherArena;
    }


    public void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowRight) {
            arena.movePaddle(arena.moveRight());
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            arena.movePaddle(arena.moveLeft());
        }
    }

    private void gameEnded(boolean gameWon){
        try{
            stopThread = true;
            endGame = true;
            if(gameWon) arena.printWon(screen.newTextGraphics());
            else arena.printLost(screen.newTextGraphics());
            screen.refresh();
            com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
            while(key.getKeyType() == KeyType.Character && toLowerCase(key.getCharacter()) != ('q')){
                key = screen.readInput();
            }
            screen.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class BallThread extends Thread{
        @Override
        public void run(){

            try{
                sleep(1000);
                while(!stopThread){
                    sleep(50);
                    draw();
                    arena.hitsPaddle();

                    if(!arena.getBall().getDestroyedBrick())
                        arena.hitsBrick();

                    screen.clear();
                    if(arena.gameLost()){gameEnded(false);}
                    if(arena.gameWon()){gameEnded(true);}
                    arena.getBall().move();
                }
            }
            catch (IOException | InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

    public void run(){
        try {
            BallThread BallThread = new BallThread();
            BallThread.start();
            while(!endGame) {
                draw();
                com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && toLowerCase(key.getCharacter()) == ('q')){
                    stopThread = true;
                    screen.close();
                    BallThread.interrupt();
                    return;
                }
                if (key.getKeyType() == KeyType.EOF)
                    break;

            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState != null)
            this.gameState.start();
    }
}
