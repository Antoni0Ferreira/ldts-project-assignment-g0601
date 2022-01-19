package com.ldts.breakout.controller;

import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.command.MenuButtonCommand;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.KeyBoardListener;
import com.ldts.breakout.viewer.state.MenuViewer;
import com.ldts.breakout.viewer.state.StateViewer;
import com.ldts.breakout.gui.GUI;

import java.io.IOException;

public class MenuController implements KeyBoardListener  {

    private final GameState gameState;
    private final StateViewer menuViewer;

    public MenuController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.menuViewer = new MenuViewer(gui,gameState.getButtons());
    }

    public void step() throws IOException {
        try {
            menuViewer.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getActiveButton(){
        for(int i = 0; i < gameState.getButtons().size(); i++){
            if(gameState.getButtons().get(i).isActive()) return i;
        }
        return -1;
    }

    @Override
    public void keyPressed(GUI.ACTION action){
        if (action == GUI.ACTION.QUIT || (gameState.getButtons().get(getActiveButton()).getPosition().getY() == 24 && action == GUI.ACTION.CHOOSE)){
            gameState.changeState(null);
            return;
        }

        if(action == GUI.ACTION.UP){
            int index = getActiveButton();
            if(index > 0){
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(index - 1).activate();
            }
            else{
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(gameState.getButtons().size()-1).activate();
            }
        }

        if(action == GUI.ACTION.DOWN){
            int index = getActiveButton();
            if(index < gameState.getButtons().size() -1){
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(index + 1).activate();
            }
            else{
                gameState.getButtons().get(index).deactivate();
                gameState.getButtons().get(0).activate();
            }
        }

        if(action == GUI.ACTION.CHOOSE){
            int index = getActiveButton();
            if(index != -1)
                gameState.getButtons().get(index).getCommand().execute();
        }
    }

}
