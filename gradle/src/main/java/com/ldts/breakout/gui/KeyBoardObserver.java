package com.ldts.breakout.gui;

import com.ldts.breakout.state.KeyBoardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoardObserver extends KeyAdapter {
    private KeyBoardListener listener;

    public KeyBoardObserver() {}

    @Override
    public void keyPressed(KeyEvent event){
        switch(event.getKeyCode()){
            case KeyEvent.VK_LEFT -> listener.keyPressed(GUI.ACTION.LEFT);
            case KeyEvent.VK_RIGHT -> listener.keyPressed(GUI.ACTION.RIGHT);
            case KeyEvent.VK_UP -> listener.keyPressed(GUI.ACTION.UP);
            case KeyEvent.VK_DOWN -> listener.keyPressed(GUI.ACTION.DOWN);
            case KeyEvent.VK_Q -> listener.keyPressed(GUI.ACTION.QUIT);
            case KeyEvent.VK_ENTER -> listener.keyPressed(GUI.ACTION.CHOOSE);
        }
    }

    public void setListener(KeyBoardListener listener){
        this.listener = listener;
    }

}
