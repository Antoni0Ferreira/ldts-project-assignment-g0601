package com.ldts.breakout.gui;

import com.ldts.breakout.state.KeyBoardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyBoardObserver extends KeyAdapter {
    private KeyBoardListener listener;

    public KeyBoardObserver() {}

    @Override
    public void keyPressed(KeyEvent event){
        switch(event.getKeyCode()){
            case KeyEvent.VK_LEFT -> {
                try {
                    listener.keyPressed(GUI.ACTION.LEFT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case KeyEvent.VK_RIGHT -> {
                try {
                    listener.keyPressed(GUI.ACTION.RIGHT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case KeyEvent.VK_UP -> {
                try {
                    listener.keyPressed(GUI.ACTION.UP);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case KeyEvent.VK_DOWN -> {
                try {
                    listener.keyPressed(GUI.ACTION.DOWN);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case KeyEvent.VK_Q -> {
                try {
                    listener.keyPressed(GUI.ACTION.QUIT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case KeyEvent.VK_ENTER -> {
                try {
                    listener.keyPressed(GUI.ACTION.CHOOSE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setListener(KeyBoardListener listener){
        this.listener = listener;
    }

}
