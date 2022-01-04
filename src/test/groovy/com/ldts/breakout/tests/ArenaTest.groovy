package com.ldts.breakout.tests

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.ldts.breakout.Arena
import com.ldts.breakout.Ball
import com.ldts.breakout.Brick
import com.ldts.breakout.Constants
import com.ldts.breakout.Paddle
import com.ldts.breakout.Points
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

    def "testing ball and brick collision #1"(){
        given:
        def ball = new Ball(new Position(Constants.INIT_BALL_X, Constants.INIT_BALL_Y),1,1)
        def brick = Mock(Brick.class)
        brick.getPosition() >> new Position(Constants.INIT_BALL_X + 5, Constants.INIT_BALL_Y -3)
        def arena = new Arena(ball)

        when:
        arena.getBall().move()
        def newPosition = ball.getPosition()

        then:
        newPosition != brick.getPosition()
        newPosition == new Position(Constants.INIT_BALL_X +1 , Constants.INIT_BALL_Y + 1)
    }

    def "testing ball and brick collision #2"(){
        given:
        def ball = new Ball(new Position(Constants.INIT_BALL_X + 4, Constants.INIT_BALL_Y - 4),1,1)
        def brick = Mock(Brick.class)
        brick.getPosition() >> new Position(Constants.INIT_BALL_X + 5, Constants.INIT_BALL_Y -3)
        def arena = new Arena(ball)

        when:
        arena.getBall().move()
        def newPosition = ball.getPosition()

        then:
        newPosition == brick.getPosition()
        newPosition == new Position(Constants.INIT_BALL_X +5 , Constants.INIT_BALL_Y - 3)
    }

    def "testing destruction of a brick #1"(){
        given:
        def ball = new Ball(new Position(Constants.INIT_BALL_X + 4, Constants.INIT_BALL_Y - 4),1,1)
        def brick = new Brick(new Position(Constants.INIT_BALL_X + 5, Constants.INIT_BALL_Y - 3))
        brick.setPoints(30)
        def bricks = new ArrayList<Brick>()
        bricks.add(brick)
        def arena = new Arena(ball,bricks)

        when:
        arena.getBall().move()
        arena.hitsBrick()
        def isDestroyed = bricks.get(0).isDestroyed()
        def points = arena.getPoints()

        then:
        isDestroyed == true
        points == 30
    }

    def "testing destruction of a brick #2"(){
        given:
        def ball = new Ball(new Position(Constants.INIT_BALL_X + 4, Constants.INIT_BALL_Y - 4),1,1)
        def brick = new Brick(new Position(Constants.INIT_BALL_X + 6, Constants.INIT_BALL_Y - 3))
        brick.setPoints(30)
        def bricks = new ArrayList<Brick>()
        bricks.add(brick)
        def arena = new Arena(ball,bricks)

        when:
        arena.getBall().move()
        arena.hitsBrick()
        def isDestroyed = bricks.get(0).isDestroyed()
        def points = arena.getPoints()

        then:
        isDestroyed == false
        points == 0
    }

    def "testing game won"(){
        given:
        def points = Mock(Points.class)
        def arena = new Arena(points)
        points.getNumPoints() >> 602
        when:
        def gameEnded = arena.gameWon()
        then:
        gameEnded == true

    }

    def "testing game lost"(){
        given:
        def ball = new Ball(new Position(10, 36), 1, 1)
        def arena = new Arena(ball)
        when:
        def gameEnded = arena.gameLost()
        then:
        gameEnded == true
    }

}