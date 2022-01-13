package com.ldts.breakout.viewer.state;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.viewer.ElementViewer;
import java.util.List;
import java.io.IOException;

public abstract class StateViewer {
    protected final GUI gui;
    private final TextGraphics textGraphics;
    protected final List<Button> buttons;

    public StateViewer(GUI gui, List<Button> buttons) {
        this.gui = gui;
        this.textGraphics = gui.createTextGraphics();
        this.buttons = buttons;
    }

    public abstract void draw() throws IOException;

    protected void drawButtons(List<Button> buttons, ElementViewer<Button> viewer){
        for (int i = buttons.size()-1; i >= 0; i--) {
            viewer.drawElement(buttons.get(i), gui);
        }
    }

    protected int getYActiveButton(){
        for(Button button:buttons){
            if(button.isActive()) return button.getPosition().getY();
        }
        return -1;
    }

    protected void drawBackground() {gui.drawBackground(textGraphics);}
}
