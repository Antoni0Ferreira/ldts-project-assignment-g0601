package com.ldts.breakout.controller

import com.ldts.breakout.Constants
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.arena.Arena
import org.mockito.Mockito
import com.ldts.breakout.model.Ball
import com.ldts.breakout.Game

import java.awt.Rectangle

class BallControllerTest extends spock.lang.Specification{
    Ball ball
    Arena arena
    ArenaController arenaController
    BallController ballController

    def setup(){
        this.ball = Mockito.mock(Ball.class)
        Mockito.doCallRealMethod().when(ball).setDirX(Mockito.anyInt())
        Mockito.doCallRealMethod().when(ball).setDirY(Mockito.anyInt())
        Mockito.doCallRealMethod().when(ball).getDirX()
        Mockito.doCallRealMethod().when(ball).getDirY()
        Mockito.doCallRealMethod().when(ball).getPosition()
        Mockito.doCallRealMethod().when(ball).getDestroyedBrick()
        Mockito.doCallRealMethod().when(ball).setDestroyedBrick(Mockito.anyBoolean())
        Mockito.doCallRealMethod().when(ball).setPosition(Mockito.any())
        ball.setDirX(1)
        ball.setDirY(-1)
        ball.setPosition(new Position(Constants.INIT_BALL_X,Constants.INIT_BALL_Y))

        this.arena = Mockito.mock(Arena.class)
        this.arenaController = Mockito.mock(ArenaController.class)
        this.ballController = Mockito.mock(BallController.class)

    }

    def "Teste movimento da Ball"(){
        given:
        def ballController = new BallController(arena, arenaController, ball)
        ballController.startBallTimer()
        sleep(1000)

        when:
        ballController.move()

        then:
        ballController.getBall().getPosition() == new Position(Constants.INIT_BALL_X + 1,Constants.INIT_BALL_Y - 1 )
        ballController.getBall().getDirX() == 1
        ballController.getBall().getDirY() == -1

        when:
        ballController.getBall().setPosition(new Position(Constants.WIDTH - 1, 1))
        ballController.move()

        then:
        ballController.getBall().getPosition() == new Position(Constants.WIDTH - 2, 2)
        ballController.getBall().getDirX() == -1
        ballController.getBall().getDirY() == 1

        when:
        ballController.getBall().setPosition(new Position(1, Constants.HEIGHT-1))
        ballController.move()

        then:
        ballController.getBall().getPosition() == new Position(2, Constants.HEIGHT-2)
        ballController.getBall().getDirX() == 1
        ballController.getBall().getDirY() == -1

    }

    def "Teste do hitPaddle"(){
        given:
        ball.setDirY(1)
        ball.setDirX(1)
        ball.setDestroyedBrick(true)
        def ballController = new BallController(arena, arenaController, ball)
        ballController.startBallTimer()
        sleep(1000)

        when:
        ballController.hitPaddle()

        then:
        -1 == ballController.getBall().getDirY()
        1 == ballController.getBall().getDirX()
        !ballController.getBall().getDestroyedBrick()

    }

    def "Teste do hit Brick"(){
        given:
        ball.setDirX(1)
        ball.setDirY(1)
        ball.setDestroyedBrick(false)
        def ballController = new BallController(arena, arenaController, ball)
        ballController.startBallTimer()
        sleep(1000)

        when:
        ballController.hitBrick()

        then:
        ballController.getBall().getDirX() == 1
        ballController.getBall().getDirY() == -1
        ballController.getBall().getDestroyedBrick()


    }

    def "Teste reset ball"(){
        given:
        ball.getPosition().setX(10)
        ball.getPosition().setY(10)
        ball.setDestroyedBrick(true)
        def ballController = new BallController(arena, arenaController, ball)
        ballController.startBallTimer()
        sleep(1000)

        when:
        ballController.resetBall()

        then:
        ballController.getBall().getPosition().getX() == Constants.INIT_BALL_X
        ballController.getBall().getPosition().getY() == Constants.INIT_BALL_Y
        !ballController.getBall().getDestroyedBrick()
    }

    def "Teste ao step #1" () {
        given:
        ball.setDestroyedBrick(false)
        arenaController.setBallController(ballController)
        def game = Mockito.mock(Game.class)
        def ballController = new BallController(arena, arenaController,ball)
        Mockito.doCallRealMethod().when(arenaController).setModel(Mockito.any())
        arenaController.setModel(arena)
        arenaController.setBallController(ballController)
        Mockito.doCallRealMethod().when(arenaController).hitsBrick()
        Mockito.doCallRealMethod().when(arenaController).hitsPaddle()
        //Mockito.doCallRealMethod().when(ballController).getRectBall()
        ball.setPosition(new Position(5,5))
        //Mockito.when(ballController.getRectBall()).thenReturn(new Rectangle(5,5,1,1))


        when:
        ballController.step(game)

        then:
        Mockito.verify(arenaController,Mockito.times(1)).hitsPaddle()
        Mockito.verify(arenaController,Mockito.times(1)).hitsBrick()

    }

    def "Teste ao step #2"(){
        given:
        this.arenaController = Mockito.mock(ArenaController.class)
        def game = Mockito.mock(Game.class)
        ball.setDestroyedBrick(true)
        def ballController = new BallController(arena, arenaController, ball)


        when:
        ballController.step(game)

        then:
        Mockito.verify(arenaController,Mockito.times(1)).hitsPaddle()
        Mockito.verify(arenaController,Mockito.times(0)).hitsBrick()

    }
}
