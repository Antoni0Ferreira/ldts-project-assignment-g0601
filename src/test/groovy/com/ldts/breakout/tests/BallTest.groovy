package com.ldts.breakout.tests

import com.ldts.breakout.Ball
import com.ldts.breakout.Constants
import com.ldts.breakout.Position

class BallTest extends spock.lang.Specification{

    def "testing move #1"(){
        given:
        def ball = new Ball() // O construtor da bola já define a posição inical da bola com x = WIDTH / 2 , y = HEIGHT -20

        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.INIT_BALL_X+1, Constants.INIT_BALL_Y +1)

    }

    def "testing move #2"(){
        given:
        def ball = new Ball(new Position(2,Constants.BORDER_TOP_Y),1,-1) // A bola está na parede de cima
        // e está a ir de baixo para cima
        // e está a ir da esquerda para a direita
        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(3,Constants.BORDER_TOP_Y+1)
    }

    def "testing move #3"(){
        given:
        def ball = new Ball(new Position(2,Constants.BORDER_TOP_Y),-1,-1) // A bola está na parede de cima
        // e está a ir de baixo para cima
        // e está a ir da direita para a esquerda
        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(1,Constants.BORDER_TOP_Y+1)
    }


    def "testing move #4"(){
        given:
        def ball = new Ball(new Position(Constants.BORDER_LEFT_X,2),-1,1)  // A bola está na parede da esquerda,
        // está a vir de cima para baixo
        // e da direita para a esquerda
        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.BORDER_LEFT_X+1,3)
    }

    def "testing move #5"(){
        given:
        def ball = new Ball(new Position(Constants.BORDER_LEFT_X,2),-1,-1)   // A bola está na parede da esquerda,
        // está a vir de baixo para cima
        // e da direita para a esquerda
        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.BORDER_LEFT_X+1,1)
    }

    def "testing move #6"(){
        given:
        def ball = new Ball(new Position(Constants.BORDER_RIGHT_X,2),1,1)   // A bola está na parede da direita,
        // está a vir de cima para baixo
        // e da esquerda para a direita
        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.BORDER_RIGHT_X-1,3)
    }

    def "testing move #7"(){
        given:
        def ball = new Ball(new Position(Constants.BORDER_RIGHT_X,2),1,-1)   // A bola está na parede da direita,
        // está a vir de baixo para cima
        // e da esquerda para a direita
        when: "the ball moves"
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.BORDER_RIGHT_X-1,1)
    }

    def "testing ball collision paddle #1"(){
        given:
        def ball = new Ball(new Position(Constants.INIT_PADDLE_X + 4, Constants.INIT_PADDLE_Y - 1),1,1)

        when: "the ball moves and collides with the paddle"
        ball.move()
        ball.hitPaddle()
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X + 6, Constants.INIT_PADDLE_Y - 1)
    }

    def "testing ball collision paddle #2"(){
        given:
        def ball = new Ball(new Position(Constants.INIT_PADDLE_X + 4, Constants.INIT_PADDLE_Y - 1),-1,1)

        when: "the ball moves and collides with the paddle"
        ball.move()
        ball.hitPaddle()
        ball.move()
        def newPosition = ball.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X + 2, Constants.INIT_PADDLE_Y - 1)
    }
}
