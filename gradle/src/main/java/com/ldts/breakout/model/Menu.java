package com.ldts.breakout.model;

public class Menu extends Element{
    public int arrow_y = 15;
    public int getArrow(){
        return arrow_y;
    }
    public enum Option {START, INST, EXIT}

    Option selected;
    Option [] opt = Option.values();

    public Menu(Position position){
        super(position);
    }

    public int getPosElem(Option target) {
        int pos = 0;
        for (int i = 0; opt[i] != target ; i++){
            if (opt[i] == target) pos = i;
        }
        return pos;
    }

    public Option getSelected() {return selected;}

    public void setSelected(Option selected) {this.selected = selected;}

    public void nextSelected() {
        if (selected == Option.EXIT) selected = Option.START;
        else {
            int i = getPosElem(selected);
            i++;
            selected = opt[i];
        }
    }

    public void previousSelected() {
        if (selected == Option.START) selected = Option.EXIT;
        else {
            int i = getPosElem(selected);
            i--;
            selected = opt[i];
        }
    }
}
