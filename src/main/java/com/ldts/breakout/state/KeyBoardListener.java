package com.ldts.breakout.state;

import com.ldts.breakout.gui.GUI;

import java.io.IOException;

public interface KeyBoardListener {
    void keyPressed(GUI.ACTION action) throws IOException;
}
