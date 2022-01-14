package com.ldts.breakout.model;

public class MenuOption extends Element{

    private char option;


    public MenuOption(char option, Position position) {
        super(position);
        this.option = option;
    }

    public char getOption() {return option;}
    public void setOption(char option) {this.option = option;}
}
