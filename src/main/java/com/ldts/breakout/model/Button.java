package com.ldts.breakout.model;
import com.ldts.breakout.model.command.Command;


public class Button extends Element {
    private final String text;
    private Command command;
    protected Boolean active = false;
    private final String color;

    public Button(Position position,String text, Command command, String color) {
        super(position);
        this.text = text;
        this.command = command;
        this.color = color;
    }

    public Position getTextPosition() {
        return position;
    }
    public String getText() {return text;}

    public String getColor(){return color;}

    public Command getCommand() {return command;}
    public void setCommand(Command command) {this.command = command;}

    public boolean isActive() {return active;}
    public void activate(){this.active = true;}
    public void deactivate(){this.active = false;}

}
