package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;

public class ButtonViewer implements ElementViewer<Button> {
    @Override
    public void drawElement(Button element, GUI gui){
        gui.drawButton(element.getPosition(), element.getTextPosition(), element.getText(), element.getColor());
    }
}
