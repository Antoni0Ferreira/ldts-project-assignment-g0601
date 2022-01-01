package com.ldts.breakout.tests

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.ldts.breakout.Arena
import com.ldts.breakout.Ball
import com.ldts.breakout.Constants
import com.ldts.breakout.Paddle
import com.ldts.breakout.Position

class ArenaTest extends spock.lang.Specification {


    def "testing moving left"() {
        given:
        def paddle = Mock(Paddle.class)
        def arena = new Arena(paddle)

        paddle.getPosition() >> new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y)

        when:
        def newPosition = arena.moveLeft()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X - 1, Constants.INIT_PADDLE_Y)
    }

    def "testing moving right"() {
        given:
        def paddle = Mock(Paddle.class)
        def arena = new Arena(paddle)

        paddle.getPosition() >> new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y)

        when:
        def newPosition = arena.moveRight()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X + 1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #1"(){

        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(arena.moveLeft())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X - 1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #2"(){
        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(arena.moveRight())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X + 1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #3"(){
        given:
        def paddle = new Paddle(new Position(1, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(arena.moveLeft())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #4"(){
        given:
        def paddle = new Paddle(new Position(Constants.BORDER_RIGHT_X - 6, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(arena.moveRight())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(Constants.BORDER_RIGHT_X - 6, Constants.INIT_PADDLE_Y)
    }

    def "testing ball and paddle movement #1"(){
        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y))
        def ball = new Ball(new Position(Constants.INIT_BALL_X, Constants.INIT_BALL_Y),1,1)
        def arena = new Arena(paddle,ball)

        when:
        arena.movePaddle(arena.moveRight())
        arena.getBall().move()
        arena.hitsPaddle()
        arena.getBall().move()
        def newPositionY = ball.getPosition().getY()

        then:
        newPositionY != Constants.INIT_BALL_Y
    }

    def "testing ball and paddle movement #2"(){
        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y))
        def ball = new Ball(new Position(Constants.INIT_PADDLE_X + 4, Constants.INIT_PADDLE_Y - 1),1,1)
        def arena = new Arena(paddle,ball)

        when:
        arena.movePaddle(arena.moveRight())
        arena.getBall().move()
        arena.hitsPaddle()
        arena.getBall().move()
        def newPositionY = ball.getPosition().getY()

        then:
        newPositionY == Constants.INIT_PADDLE_Y - 1
    }

}