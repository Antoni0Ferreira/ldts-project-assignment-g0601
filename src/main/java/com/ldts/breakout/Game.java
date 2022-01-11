package com.ldts.breakout;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;

public class Game {
    private Screen screen;
    private final Arena arena;
    private boolean stopThread = false;
    private boolean endGame = false;
    private int speed = 60;

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

    // Only for tests
    public Game(Arena arena){
        this.arena = arena;
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

    private void updateSpeed(){
        if(speed > 30)
            speed =  60 - (arena.getPoints() / 30) * 2;
    }

    public class BallThread extends Thread{
        @Override
        public void run(){
                try {
                    while (arena.getLives() > 0) {
                        arena.getBall().setPosition(new Position(Constants.INIT_BALL_X, Constants.INIT_BALL_Y));
                        draw();
                        sleep(1000);
                        while (!stopThread) {
                            sleep(speed);
                            draw();
                            arena.hitsPaddle();

                            if (!arena.getBall().getDestroyedBrick())
                                arena.hitsBrick();
                            updateSpeed();
                            screen.clear();
                            if (arena.gameLost()) {
                                arena.loseLive();
                                break;
                            }
                            if (arena.gameWon()) {
                                gameEnded(true);
                            }
                            arena.getBall().move();
                        }
                    }
                }
            catch (IOException | InterruptedException e) {
                    e.printStackTrace();
            }

            gameEnded(false);
        }
    }

    public void run() {
            try {
                BallThread BallThread = new BallThread();
                BallThread.start();
                while (!endGame) {
                    draw();
                    com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                    processKey(key);
                    if (key.getKeyType() == KeyType.Character && toLowerCase(key.getCharacter()) == ('q')) {
                        stopThread = true;
                        screen.close();
                        BallThread.interrupt();
                        return;
                    }
                    if (key.getKeyType() == KeyType.EOF)
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    }

