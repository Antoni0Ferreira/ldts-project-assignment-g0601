package com.ldts.breakout.controller

import com.ldts.breakout.Constants
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.gui.LanternaTest
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.arena.Arena
import com.ldts.breakout.state.GameState
import com.ldts.breakout.state.MenuState
import com.ldts.breakout.viewer.ArenaViewer
import org.mockito.Mockito
import com.ldts.breakout.model.*

class ArenaControllerTest extends spock.lang.Specification{
    def arenaController

    def setup(){
        def gameState = Mockito.mock(GameState.class)
        def ballController = Mockito.mock(BallController.class)
        def paddleController = Mockito.mock(PaddleController.class)
        def arenaViewer = Mockito.mock(ArenaViewer.class)
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
        Mockito.doCallRealMethod().when(ballController).step(Mockito.any())
        this.arenaController = new ArenaController(gameState,gui,arena)
        arenaController.setPaddleController(paddleController)
        arenaController.setBallController(ballController)
        arenaController.setArenaViewer(arenaViewer)
    }

    def "Teste ao doAction"(){
        given:
        def paddle = arenaController.getPaddleController.getPaddle()

        when:
        paddle.setPosition(new Position(2,2))
        def action = GUI.ACTION.LEFT
        arenaController.doAction()

        then:
        paddle.getPosition == new Position(1,2)

    }

    def "Teste ao doAction #2"(){
        given:
        def paddle = arenaController.getPaddleController.getPaddle()

        when:
        paddle.setPosition(new Position(2,2))
        def action = GUI.ACTION.RIGHT
        arenaController.doAction()

        then:
        paddle.getPosition == new Position(3,2)

    }


    def "Teste ao hits Paddle"(){
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

    def "Teste ao hits Brick"(){
        given:
        def brickList = Arrays.asList(Brick)
        def brick = Mockito.mock(Brick.class)
        Mockito.doCallRealMethod().when(brick.setPosition(Mockito.any()))
        Mockito.when(brick.isDestroyed()).thenReturn(false)
        brick.setPosition(new Position(2,3))
        bricksList.add(brick)
        arenaController.setBricks(brickList)

        def ball = Mockito.mock(Ball.class)
        Mockito.doCallRealMethod().when(ball.setPosition(Mockito.any()))
        ball.setPosition(new Position(3,4))

        def ballController = Mockito.mock(BallController.class)
        Mockito.doCallRealMethod().when(ballController.setBall(Mockito.any()))
        ballController.setBall(ball)

        when:
        def hitBrick = arenaController.hitsBrick()

        then:
        hitBrick == false

        when:
        ball.setPosition(new Position(2,3))
        ballController.setBall(ball)
        hitBrick = arenaController.hitsBrick()

        then:
        hitBrick == true

        when:
        brick.setDestroyed(true)
        bricksList = Arrays.asList(Brick)
        bricksList.add(brick)
        hitBrick = arenaController.hitsBrick()

        then:
        hitBrick == false

    }

    def "Teste ao lost life"(){
        given:
        def ball = Mockito.mock(Ball.class)
        Mockito.doCallRealMethod().when(ball).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(ball).getPosition()
        ball.setPosition(new Position(5, 36))

        def ballController = Mockito.mock(BallController.class)
        Mockito.doCallRealMethod().when(ballController).setBall(Mockito.any())
        ballController.setBall(ball)

        def paddle = Mockito.mock(Paddle.class)
        Mockito.doCallRealMethod().when(paddle).setLives(Mockito.any())
        paddle.setLives(3)
        def paddleController = Mockito.mock(PaddleController.class)
        Mockito.doCallRealMethod().when(paddleController.setPaddle(paddle))
        arenaController.setPaddleController(paddleController)
        arenaController.setBallController(ballController)
        when:
        arenaController.lostLife()
        then:
        arenaController.getPaddleController().getPaddle().getLives() == 2

    }

    def "Teste ao step #1"() {

        given:
        def endGameViewer = Mockito.mock(endGameViewer.class)
        Mockito.when(paddleController.getPaddle().getLives()).thenReturn(0)

        when:
        arenaController.step()

        then:
        Mockito.verify(ballController,Mockito.times(1)).step()
        Mockito.verify(arenaViewer,Mockito.times(1)).draw()
        Mockito.verify(endGameViewer,Mockito.times(1)).draw()
        Mockito.verify(gameState,Mockito.times(1)).changeState(new MenuState(this.gameState.getGame(),arenaViewer.getGui()))

        //Mockito.doCallRealMethod().when(arenaController).lostLife()
        //Mockito.doCallRealMethod().when(arenaController).step()
        //Mockito.doCallRealMethod().when(arenaViewer).draw()

    }

    def "Teste ao step #2" () {

        given:
        endGameViewer = Mockito.mock(endGameViewer.class)
        Mockito.when(paddleController.getPaddle().getLives()).thenReturn(Constants.MAX_POINTS)

        when:
        arenaController.step()

        then:
        Mockito.verify(ballController,Mockito.times(1)).step()
        Mockito.verify(arenaViewer,Mockito.times(1)).draw()
        Mockito.verify(endGameViewer,Mockito.times(1)).draw()
        Mockito.verify(gameState,Mockito.times(1)).changeState(new MenuState(this.gameState.getGame(),arenaViewer.getGui()))
    }
}


