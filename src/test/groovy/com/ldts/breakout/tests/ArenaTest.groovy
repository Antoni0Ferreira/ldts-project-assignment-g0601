package com.ldts.breakout.tests

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.ldts.breakout.Arena
import com.ldts.breakout.Constants
import com.ldts.breakout.Paddle
import com.ldts.breakout.Position

class ArenaTest extends spock.lang.Specification{

    def "testing moving paddle left"(){
        given:
        def position = Mock(Position.class)
        def paddle = new Paddl(position);

        paddle.getPosition().getX() >> Constants.INIT_PADDLE_X
        paddle.getPosition().getY() >> Constants.INIT_PADDLE_Y

        when:
        def newPositionX = paddle.moveLeft().getX()
        def newPositionY = paddle.moveLeft().getY()

        then:
        newPositionX == Constants.INIT_PADDLE_X - 1
        newPositionY == Constants.INIT_PADDLE_Y

    }

    def "test moving paddle right"(){
        given:
        def position = Mock(Position.class)
        def paddle = new Paddle(position);

        paddle.getPosition().getX() >> Constants.INIT_PADDLE_X
        paddle.getPosition().getY() >> Constants.INIT_PADDLE_Y

        when:
        def newPositionX = paddle.moveLeft().getX()
        def newPositionY = paddle.moveLeft().getY()

        then:
        newPositionX == Constants.INIT_PADDLE_X - 1
        newPositionY == Constants.INIT_PADDLE_Y
    }

    def "testing paddle movement #1"(){
        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(paddle.moveLeft())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X - 1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #2"(){
        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(paddle.moveRight())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X + 1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #3"(){
        given:
        def paddle = new Paddle(new Position(1, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(paddle.moveLeft())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(1, Constants.INIT_PADDLE_Y)
    }

    def "testing paddle movement #4"(){
        given:
        def paddle = new Paddle(new Position(Constants.BORDER_RIGHT_X - 6, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when:
        arena.movePaddle(paddle.moveRight())
        def newPosition = paddle.getPosition()

        then:
        newPosition == new Position(Constants.BORDER_RIGHT_X - 6, Constants.INIT_PADDLE_Y)
    }

    def "testing process key #1"(){
        given:
        def key = Mock(KeyStroke.class)
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y))
        def arena = new Arena(paddle)

        when: "we want to move the paddle to the left"
        key.getKeyType() >> KeyType.ArrowLeft
        arena.movePaddle(paddle.moveLeft())
    }
}
