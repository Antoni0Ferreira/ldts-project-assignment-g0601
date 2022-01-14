package com.ldts.breakout.state;
import com.ldts.breakout.controller.InstructionsController;
import com.ldts.breakout.Game;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.command.MenuButtonCommand;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class InstructionsState extends GameState{
        private InstructionsController instructionsController;

        public InstructionsState(Game game, GUI gui) throws IOException {
                super(game, Arrays.asList(new Button(new Position(4,35),"PLAY GAME (PRESS ENTER)", new MenuButtonCommand(new PlayingState(game,gui)),"#FFFFFF")));
                this.instructionsController = new InstructionsController(this,gui);
                getButtons().get(0).activate();
        }

        @Override
        public void start() {
                game.getKeyBoardObserver().setListener(instructionsController);
        }

        @Override
        public void step(Game game, long time) throws IOException {
                instructionsController.step();
        }

        public InstructionsController getInstructionsController() {
                return instructionsController;
        }

        public void setInstructionsController(InstructionsController instructionsController) {
                this.instructionsController = instructionsController;
        }
}
