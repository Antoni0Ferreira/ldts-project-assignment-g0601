package com.ldts.breakout.controller

import com.ldts.breakout.Constants
import com.ldts.breakout.model.Brick
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.arena.Arena
import org.mockito.Mockito
import com.ldts.breakout.model.Ball
import com.ldts.breakout.Game
import com.ldts.breakout.model.Paddle

import java.awt.Rectangle
import java.rmi.MarshalledObject

class BallControllerTest extends spock.lang.Specification{
    Ball ball
    Arena arena
    ArenaController arenaController
    BallController ballController

    def setup(){
        this.ball = new Ball()
        ball.setDirY(-1)
        this.arena = Mockito.mock(Arena.class)
        this.arenaController = Mockito.mock(ArenaController.class)
        this.ballController = new BallController(arena,arenaController,ball)

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
        def game = Mockito.mock(Game.class)
        ballController.getBall().setDestroyedBrick(false)
        ballController.setArenaController(arenaController)

        when:
        ballController.step(game)

        then:
        ball.getPosition() == new Position(Constants.INIT_BALL_X + ball.getDirX(), Constants.INIT_BALL_Y + ball.getDirY())


    }

    def "Teste ao step #2"(){
        given:
        def game = Mockito.mock(Game.class)
        ball.setDestroyedBrick(true)

        def paddle = Mockito.mock(Paddle.class)
        def paddleController = Mockito.mock(PaddleController.class)
        Mockito.when(paddle.getRect()).thenReturn(new Rectangle(Constants.INIT_BALL_X - 2, Constants.INIT_BALL_Y, 7, 1))
        Mockito.when(paddleController.getPaddle()).thenReturn(paddle)
        Mockito.doCallRealMethod().when(arenaController).hitsPaddle()

        def ballController = new BallController(arena,arenaController,ball)

        Mockito.doCallRealMethod().when(arenaController).setPaddleController(Mockito.any())
        arenaController.setPaddleController(paddleController)
        Mockito.doCallRealMethod().when(arenaController).setBallController(Mockito.any())
        arenaController.setBallController(ballController)

        when:
        ballController.step(game)

        then:
        ballController.getBall().getDirY() == 1
        ballController.getBall().getDirX() == 1

        when:
        ballController.getBall().setPosition(new Position(5, 5))
        ballController.step(game)
        then:
        ballController.getBall().getDirY() == 1
        ballController.getBall().getDirX() == 1


    }

    def "Teste ao step #3"(){
        given:
        def game = Mockito.mock(Game.class)
        ball.setDestroyedBrick(false)

        def paddle = Mockito.mock(Paddle.class)
        def paddleController = Mockito.mock(PaddleController.class)

        def brick = Mockito.mock(Brick.class)
        Mockito.when(brick.getRect()).thenReturn(new Rectangle(Constants.INIT_BALL_X - 3, Constants.INIT_BALL_Y, 7, 1))
        Mockito.doCallRealMethod().when(arenaController).setBrickList(Mockito.anyList())
        arenaController.setBrickList(Arrays.asList(brick))

        Mockito.doCallRealMethod().when(arenaController).hitsBrick()

        def ballController = new BallController(arena,arenaController,ball)

        Mockito.doCallRealMethod().when(arenaController).setBallController(Mockito.any())
        arenaController.setBallController(ballController)
        Mockito.doCallRealMethod().when(arenaController).setPaddleController(Mockito.any())
        arenaController.setPaddleController(paddleController)

        when:
        ballController.step(game)

        then:
        ballController.getBall().getDirY() == 1
        ballController.getBall().getDirX() == 1

        when:
        ballController.getBall().setPosition(new Position(5, 5))
        ballController.step(game)
        then:
        ballController.getBall().getDirY() == 1
        ballController.getBall().getDirX() == 1

        when:
        ball.setDestroyedBrick(true)
        ballController.step(game)

        then:
        ballController.getBall().getDirY() == 1
        ballController.getBall().getDirX() == 1



    }
}
