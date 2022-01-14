package com.ldts.breakout.viewer.state;

import com.ldts.breakout.Constants;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Button;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.viewer.ButtonViewer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class EndGameViewer extends StateViewer {
    private final boolean won;

    public EndGameViewer(GUI gui, List<Button> buttons, boolean won) {
        super(gui, buttons);
        this.won = won;
    }

    @Override
    public void draw(){
        try {
            gui.clear();
            gui.createTextGraphics();
<<<<<<< HEAD
            drawBackground();
            drawButtons(buttons, new ButtonViewer());
            gui.drawTitle(new Position(1, getYActiveButton()), "&'", "#FF0000");
            if (!won) {
                gui.drawTitle(new Position(Constants.WIDTH / 2 - 3, Constants.HEIGHT / 2 - 3), "YOU ", "#FFFFFF");
                gui.drawTitle(new Position(Constants.WIDTH / 2 + 1 , Constants.HEIGHT / 2 - 3), "LOST", "#FF0000");
                gui.drawTitle(new Position(Constants.WIDTH / 2 + 5 , Constants.HEIGHT / 2 - 3), "!", "#FFFFFF");
                gui.drawTitle(new Position(Constants.WIDTH / 2 - 6, Constants.HEIGHT / 2), "PRESS Q TO EXIT", "#FFFFFF");
=======
            if (!won) {
                gui.drawTitle(new Position(Constants.WIDTH / 2 - 5, Constants.HEIGHT / 2 - 3), "YOU LOST!", "#FF0000");
                gui.drawTitle(new Position(Constants.WIDTH / 2 - 8, Constants.HEIGHT / 2), "PRESS Q TO EXIT", "#FF0000");
>>>>>>> e5d77fcda06d9c75df03301ae5ac37949d3f422c
            } else {
                gui.drawTitle(new Position(Constants.WIDTH / 2 - 3, Constants.HEIGHT / 2 - 3), "YOU WON!", "#0066FF");
                gui.drawTitle(new Position(Constants.WIDTH / 2 - 7, Constants.HEIGHT / 2), "PRESS Q TO EXIT", "#0066FF");
            }
            gui.refresh();
<<<<<<< HEAD

        } catch (Exception e) {
            System.out.println("ERROR");
        }
=======
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (Exception e) {
            System.out.println("ERROR");
        }

>>>>>>> e5d77fcda06d9c75df03301ae5ac37949d3f422c
    }
}
