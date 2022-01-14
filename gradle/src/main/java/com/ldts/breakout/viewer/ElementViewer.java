package com.ldts.breakout.viewer;

import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Element;

public interface ElementViewer<T extends Element>{
    void drawElement(T element, GUI gui);
}
