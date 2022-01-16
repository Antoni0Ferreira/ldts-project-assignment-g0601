package com.ldts.breakout.controller

import com.ldts.breakout.Constants
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.model.Paddle
import com.ldts.breakout.model.Position
import org.mockito.Mockito

class PaddleControllerTest extends spock.lang.Specification{

    def "Teste ao movimento do paddle"(){
        given:
        def paddle = Mockito.mock(Paddle.class)
        def paddleController = new PaddleController(paddle)
        Mockito.when(paddle.getPosition()).thenReturn(new Position(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y))

        when:
        def x = paddleController.moveLeft().getX()
        def y = paddleController.moveLeft().getY()

        then:
        x == Constants.INIT_PADDLE_X - 1
        y == Constants.INIT_PADDLE_Y

        when:
        x = paddleController.moveRight().getX()
        y = paddleController.moveRight().getY()

        then:
        x == Constants.INIT_PADDLE_X + 1 //porque o getPosition da sempre a posicao inicial
        y == Constants.INIT_PADDLE_Y
    }

    def "Teste movimentador do paddle"(){
        given:
        def positionLeft = new Position(0, Constants.INIT_PADDLE_Y)
        def positionRight = new Position(50, Constants.INIT_PADDLE_Y)
        def positionValid = new Position(10, Constants.INIT_PADDLE_Y)
        def paddle = Mockito.mock(Paddle.class)
        def paddleController = new PaddleController(paddle)
        Mockito.doCallRealMethod().when(paddle).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(paddle).getPosition()

        when:
        paddleController.movePaddle(new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y))
        paddleController.movePaddle(positionLeft)

        then:
        paddleController.getPaddle().getPosition().getX() == Constants.INIT_PADDLE_X
        paddleController.getPaddle().getPosition().getY() == Constants.INIT_PADDLE_Y

        when:
        paddleController.movePaddle(positionRight)

        then:
        paddleController.getPaddle().getPosition().getX() == Constants.INIT_PADDLE_X
        paddleController.getPaddle().getPosition().getY() == Constants.INIT_PADDLE_Y

        when:
        paddleController.movePaddle(positionValid)

        then:
        paddleController.getPaddle().getPosition().getX() == 10
        paddleController.getPaddle().getPosition().getY() == Constants.INIT_PADDLE_Y

    }


    def "Teste à Verificação do Movimento do Paddle"(){
        given:
        def paddle = Mockito.mock(Paddle.class)
        def paddleController = new PaddleController(paddle)
        Mockito.doCallRealMethod().when(paddle).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(paddle).getPosition()

        when:
        paddle.setPosition(new Position(1,1))
        def newPosition = paddleController.moveLeft()
        def canPaddleMove = paddleController.canPaddleMove(newPosition)

        then:
        canPaddleMove == false

        when:
        newPosition = paddleController.moveRight()
        canPaddleMove = paddleController.canPaddleMove(newPosition)

        then:
        canPaddleMove == true

        when:
        paddle.setPosition(new Position(49,1))
        newPosition = paddleController.moveRight()
        canPaddleMove = paddleController.canPaddleMove(newPosition)

        then:
        canPaddleMove == false

        when:
        newPosition = paddleController.moveLeft()
        canPaddleMove = paddleController.canPaddleMove(newPosition)

        then:
        canPaddleMove == true

        when:
        paddle.setPosition(new Position(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y))
        newPosition = paddleController.moveLeft()
        canPaddleMove = paddleController.canPaddleMove(newPosition)

        then:
        canPaddleMove == true

        when:
        newPosition = paddleController.moveRight()
        canPaddleMove = paddleController.canPaddleMove(newPosition)

        then:
        canPaddleMove == true
    }

    def "Teste ao doAction"(){
        given:
        def paddle = Mockito.mock(Paddle.class)
        def paddleController = new PaddleController(paddle)
        Mockito.doCallRealMethod().when(paddle).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(paddle).getPosition()

        when:
        paddle.setPosition(new Position(2,2))
        def action = GUI.ACTION.LEFT
        def newPosition = paddleController.doAction(action)

        then:
        newPosition == new Position(1,2)

        when:
        action = GUI.ACTION.RIGHT
        newPosition = paddleController.doAction(action)

        then:
        newPosition == new Position(3,2)
    }

    def "Teste do lost life"(){
        given:
        def paddle = Mockito.mock(Paddle.class)
        Mockito.doCallRealMethod().when(paddle).setLives(Mockito.anyInt())
        Mockito.doCallRealMethod().when(paddle).updateLives()
        Mockito.doCallRealMethod().when(paddle).getLives()
        paddle.setLives(3)
        def paddleController = new PaddleController(paddle)

        when:
        paddleController.lostLife()

        then:
        paddleController.getPaddle().getLives() == 2
    }

    def "Teste do add Points"(){
        given:
        def paddle = Mockito.mock(Paddle.class)
        Mockito.doCallRealMethod().when(paddle).setPoints(Mockito.anyInt())
        Mockito.doCallRealMethod().when(paddle).updatePoints(Mockito.anyInt())
        Mockito.doCallRealMethod().when(paddle).getPoints()
        paddle.setPoints(0)
        def paddleController = new PaddleController(paddle)

        when:

        paddleController.addPoints(50)

        then:
        paddleController.getPaddle().getPoints() == 50

    }
}
