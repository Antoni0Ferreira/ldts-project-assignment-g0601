package com.ldts.breakout.controller

import com.ldts.breakout.Constants
import com.ldts.breakout.Game
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.model.arena.Arena
import com.ldts.breakout.state.GameState
import com.ldts.breakout.state.MenuState
import com.ldts.breakout.viewer.ArenaViewer
import com.ldts.breakout.viewer.state.EndGameViewer
import org.mockito.Mockito

import com.ldts.breakout.model.*

import java.awt.Rectangle

class ArenaControllerTest extends spock.lang.Specification{
    def arenaController
    def paddleController

    def setup(){
        def gameState = Mockito.mock(GameState.class)
        def gui = Mockito.mock(LanternaGUI)
        def arena = new Arena()
        this.arenaController = new ArenaController(gameState,gui,arena)
    }

    def "Teste ao doAction"(){
        given:
        def paddle = Mockito.mock(Paddle.class)

        Mockito.doCallRealMethod().when(paddle).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(paddle).getPosition()
        paddle.setPosition(new Position(2,2))

        def paddleController = new PaddleController(paddle)

        arenaController.setPaddleController(paddleController)

        when:
        def action = GUI.ACTION.LEFT
        arenaController.doAction(action)


        then:
        paddleController.getPaddle().getPosition() == new Position(1,2)

    }

    def "Teste ao doAction #2"(){
        given:
        def paddle = Mockito.mock(Paddle.class)

        Mockito.doCallRealMethod().when(paddle).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(paddle).getPosition()
        paddle.setPosition(new Position(2,2))

        def paddleController = new PaddleController(paddle)

        arenaController.setPaddleController(paddleController)

        when:
        def action = GUI.ACTION.RIGHT
        arenaController.doAction(action)


        then:
        paddleController.getPaddle().getPosition() == new Position(3,2)

    }


    def "Teste ao hits Paddle"(){
        given:
        def ballController = Mockito.mock(BallController.class)
        Mockito.when(ballController.getRectBall()).thenReturn(new Rectangle(5,5,1,1))

        def paddle = Mockito.mock(Paddle.class)
        Mockito.when(paddle.getPosition()).thenReturn(new Position(3,5))
        Mockito.when(paddle.getRect()).thenReturn(new Rectangle(3,5,7,1))
        def paddleController = Mockito.mock(PaddleController.class)
        Mockito.when(paddleController.getPaddle()).thenReturn(paddle)

        arenaController.setBallController(ballController)
        arenaController.setPaddleController(paddleController)

        when:
        def x = arenaController.hitsPaddle()

        then:
        x == true

        when:
        Mockito.when(ballController.getRectBall()).thenReturn(new Rectangle(10,10,1,1))

        then:
        arenaController.hitsPaddle() == false

    }

    def "Teste ao hits Brick"(){
        given:
        def brick = Mockito.mock(Brick.class)
        Mockito.when(brick.getRect()).thenReturn(new Rectangle(2,3,7,1))
        Mockito.when(brick.getPoints()).thenReturn(1)

        Mockito.when(brick.isDestroyed()).thenReturn(false)

        List<Brick> brickList = Arrays.asList(brick)
        arenaController.setBrickList(brickList)

        def ballController = Mockito.mock(BallController.class)
        Mockito.when(ballController.getRectBall()).thenReturn(new Rectangle(3,4,1,1))

        def paddleController = Mockito.mock(PaddleController.class)
        def paddle = Mockito.mock(Paddle.class)
        Mockito.when(paddleController.getPaddle()).thenReturn(paddle)

        arenaController.setBallController(ballController)
        arenaController.setPaddleController(paddleController)

        when:
        def hitBrick = arenaController.hitsBrick()

        then:
        hitBrick == false

        when:
        Mockito.when(ballController.getRectBall()).thenReturn(new Rectangle(2,3,1,1))
        hitBrick = arenaController.hitsBrick()

        then:
        hitBrick == true

        when:
        brick = Mockito.mock(Brick.class)
        Mockito.when(brick.getRect()).thenReturn(new Rectangle(2,3,7,1))
        Mockito.when(brick.getPoints()).thenReturn(1)

        Mockito.when(brick.isDestroyed()).thenReturn(true)

        brickList = Arrays.asList(brick)
        arenaController.setBrickList(brickList)
        hitBrick = arenaController.hitsBrick()

        then:
        hitBrick == false

    }

    def "Teste ao lost life"(){
        given:
        def ball = Mockito.mock(Ball.class)
        Mockito.when(ball.getPosition()).thenReturn(new Position(5, 36))

        def ballController = Mockito.mock(BallController.class)
        Mockito.when(ballController.getBall()).thenReturn(ball)

        def paddle = Mockito.mock(Paddle.class)
        Mockito.doCallRealMethod().when(paddle).setLives(Mockito.anyInt())
        Mockito.when(paddle.getLives()).thenReturn(2)
        paddle.setLives(3)

        def paddleController = Mockito.mock(PaddleController.class)
        Mockito.doCallRealMethod().when(paddleController).setPaddle(Mockito.any())
        Mockito.when(paddleController.getPaddle()).thenReturn(paddle)
        paddleController.setPaddle(paddle)

        arenaController.setPaddleController(paddleController)
        arenaController.setBallController(ballController)

        when:
        arenaController.lostLife()
        then:
        arenaController.getPaddleController().getPaddle().getLives() == 2

    }

    def "Teste ao step"() {
        given:
        def ball = Mockito.mock(Ball.class)
        def ballController = Mockito.mock(BallController.class)
        def game = new Game(20)
        def arenaViewer = Mockito.mock(ArenaViewer.class)

        Mockito.when(ballController.getBall()).thenReturn(ball)
        Mockito.when(ball.getPosition()).thenReturn(new Position(5,5))

        arenaController.setBallController(ballController)
        arenaController.setArenaViewer(arenaViewer)

        when:
        arenaController.step(game)

        then:
        Mockito.verify(ballController,Mockito.times(1)).step(game)
    }
}


