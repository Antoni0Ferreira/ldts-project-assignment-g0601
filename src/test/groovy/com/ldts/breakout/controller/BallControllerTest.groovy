package com.ldts.breakout.controller

import com.ldts.breakout.Constants
import com.ldts.breakout.model.Position
import org.mockito.Mockito
import com.ldts.breakout.model.Ball

class BallControllerTest extends spock.lang.Specification{


    def setup(){
        def ball = Mockito.mock(Ball.class)
        Mockito.doCallRealMethod().when(ball).setDirX(Mockito.anyInt())
        Mockito.doCallRealMethod().when(ball).setDirY(Mockito.anyInt())
        Mockito.doCallRealMethod().when(ball).getDirX()
        Mockito.doCallRealMethod().when(ball).getDirY()
        Mockito.doCallRealMethod().when(ball).getPosition()
        Mockito.doCallRealMethod().when(ball).getPosition().getY()
        Mockito.doCallRealMethod().when(ball).getPosition().getX()
        Mockito.doCallRealMethod().when(ball).getPosition().setX(Mockito.anyInt())
        Mockito.doCallRealMethod().when(ball).getPosition().setY(Mockito.anyInt())
        Mockito.doCallRealMethod().when(ball).getDestroyedBrick()
        Mockito.doCallRealMethod().when(ball).setDestroyedBrick(Mockito.anyBoolean())

    }

    def "Teste movimento da Ball"(){
        given:
        ball.setDirX(1)
        ball.setDirY(-1)
        ball.getPosition().setX(Constants.INIT_BALL_X)
        ball.getPosition().setY(Constants.INIT_BALL_Y)
        def ballController = new BallController(ball)


        when:
        ballController.move()

        then:
        ballController.getBall().getPosition() == new Position(Constants.INIT_BALL_X + 1,Constants.INIT_BALL_Y - 1 )
        ballController.getBall.getDirX() == 1
        ballController.getBall.getDirY() == -1

        when:
        ballController.getBall().setPosition(new Position(Constants.WIDTH - 1, 1))
        ballController.move()

        then:
        ballController.getBall().getPosition() == new Position(Constants.WIDTH - 2, 2)
        ballController.getBall.getDirX() == -1
        ballController.getBall.getDirY() == 1

        when:
        ballController.getBall().setPosition(new Position(1, Constants.HEIGHT-1))
        ballController.move()

        then:
        ballController.getBall().getPosition() == new Position(2, Constants.HEIGHT-2)
        ballController.getBall.getDirX() == 1
        ballController.getBall.getDirY() == -1


    }

    def "Teste do hitPaddle"(){
        given:
        ball.setDirY(1)
        ball.setDirX(1)
        ball.setDestroyedBrick(true)
        def ballControlLer = new BallController(ball)

        when:
        ballControlLer.hitPaddle()

        then:
        -1 == ballControlLer.getBall().getDirY()
        1 == ballControlLer.getBall().getDirX()
        false == ballControlLer.getBall().getDestroyedBrick()

    }

    def "Teste do hit Brick"(){
        given:
        ball.setDirX(1)
        ball.setDirY(1)
        ball.setDestroyedBrick(false)
        def ballController = new BallController(ball)

        when:
        ballController.hitBrick()

        then:
        ballController.getBall().getDirX() == 1
        ballController.getBall().getDirX() == -1
        ballControlLer.getBall().getDestroyedBrick() == true


    }

}
