package com.ldts.breakout.controller

import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.gui.LanternaTest
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.arena.Arena
import com.ldts.breakout.state.GameState
import org.mockito.Mockito
import com.ldts.breakout.model.*

class ArenaControllerTest extends spock.lang.Specification{
    def arenaController

    def setup(){
        def gameState = Mockito.mock(GameState.class)
        def ballController = Mockito.mock(BallController.class)
        def paddleController = Mockito.mock(PaddleController.class)
        def brickList = Arrays.asList()
        def gui = new LanternaGUI()
        def arena = new Arena()
        Mockito.doCallRealMethod().when(gameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(paddleController).doAction(Mockito.any(GUI.ACTION))
        Mockito.doCallRealMethod().when(paddleController).movePaddle(Mockito.any())
        Mockito.doCallRealMethod().when(paddleController).lostLife()
        Mockito.doCallRealMethod().when(paddleController).addPoints(Mockito.anyInt())
        Mockito.doCallRealMethod().when(paddleController).getPaddle()
        Mockito.doCallRealMethod().when(ballController).getRectBall()
        Mockito.doCallRealMethod().when(ballController).hitPaddle()
        Mockito.doCallRealMethod().when(ballController).hitBrick()
        Mockito.doCallRealMethod().when(ballController).resetBall()
        Mockito.doCallRealMethod().when(ballController).getBall()
        this.arenaController = new ArenaController(gameState,gui,arena)
        arenaController.setPaddleController(paddleController)
        arenaController.setBallController(ballController)
    }

    def "Teste ao doAction"(){
        given:
        def paddle = arenaController.getPaddleController.getPaddle()

        when:
        paddle.setPosition(new Position(2,2))
        def action = GUI.ACTION.LEFT
        arenaController.doAction()

        then:
        paddle.getPosition == newPosition(1,2)

        when:
        action = GUI.ACTION.RIGHT
        arenaController.doAction()

        then:
        paddle.getPosition == newPosition(3,2)

    }


    def "Teste ao hits paddle"(){
        given:
        def ball = Mockito.mock(Ball.class)
        ball.setPosition(new Position(5, 5))
        def ballController = Mockito.mock(BallController.class)
        ballController.setBall(ball)
        def paddle = Mockito.mock(Paddle.class)
        paddle.setPosition(new Position(3,5))
        def arenaController = new ArenaController()
        arenaController.setBallController(ballController)
        arenaController.setPaddleController(paddleController)
        when:
        def x = arenaController.hitsPaddle()
        then:
        x == true
        when:
        arenaController.getBallController().getBall().setPosition(new Position(10, 10))
        then:
        arenaController.hitsPaddle() == false



    }

}


