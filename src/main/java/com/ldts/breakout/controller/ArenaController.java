package com.ldts.breakout.controller;

import com.ldts.breakout.Constants;
import com.ldts.breakout.Game;
import com.ldts.breakout.gui.GUI;
import com.ldts.breakout.model.Brick;
import com.ldts.breakout.model.Position;
import com.ldts.breakout.model.arena.Arena;
import com.ldts.breakout.state.GameState;
import com.ldts.breakout.state.MenuState;
import com.ldts.breakout.viewer.ArenaViewer;
import com.ldts.breakout.viewer.state.EndGameViewer;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;

public class ArenaController extends GameController {
    private final GameState gameState;
    private final ArenaViewer arenaViewer;
    private BallController ballController;
    private PaddleController paddleController;
    private List<Brick> brickList;

    public ArenaController(GameState gameState, GUI gui, Arena arena){
        super(arena);
        this.gameState = gameState;
        this.arenaViewer = new ArenaViewer(gui,arena);
        this.ballController = new BallController(arena,this,arena.getBall());
        this.paddleController = new PaddleController(arena.getPaddle());
        this.brickList = arena.getBricks();
    }

    public PaddleController getPaddleController(){return paddleController;}

    public void doAction(GUI.ACTION action){
        if(action == GUI.ACTION.QUIT){
            gameState.changeState(null);
        }
        Position nextPosition = paddleController.doAction(action);
        paddleController.movePaddle(nextPosition);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        ballController.step(game, time);
        lostLife();
        arenaViewer.draw();
        if(paddleController.getPaddle().getLives() == 0){
            EndGameViewer endGameViewer = new EndGameViewer(arenaViewer.getGui(),null,false);
            endGameViewer.draw();
            gameState.changeState(new MenuState(this.gameState.getGame(), arenaViewer.getGui()));
        }
        else if(paddleController.getPaddle().getPoints() == Constants.MAX_POINTS){
            EndGameViewer endGameViewer = new EndGameViewer(arenaViewer.getGui(),null,true);
            endGameViewer.draw();
            gameState.changeState(new MenuState(this.gameState.getGame(), arenaViewer.getGui()));
        }

    }

    public boolean hitsPaddle(){
        if(ballController.getRectBall().intersects(paddleController.getPaddle().getRect())){
            ballController.hitPaddle();
            return true;
        }
        return false;
    }

    public boolean hitsBrick(){
        for(Brick brick: brickList){
            if(ballController.getRectBall().intersects(brick.getRect()) && !brick.isDestroyed()){
                ballController.hitBrick();
                brick.setDestroyed(true);
                paddleController.addPoints(brick.getPoints());
                brickList.remove(brick);
                return true;
            }
        }
        return false;
    }

    public void lostLife(){
        if(ballController.getModel().getBall().getPosition().getY() > paddleController.getPaddle().getPosition().getY()){
            paddleController.lifeLost();
            ballController.resetBall();
        }
    }

    public BallController getBallController(){
        return ballController;
    }



}
