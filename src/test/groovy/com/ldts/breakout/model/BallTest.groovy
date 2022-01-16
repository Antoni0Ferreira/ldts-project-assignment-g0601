package com.ldts.breakout.model

import com.ldts.breakout.Constants
import com.ldts.breakout.Ball
import com.ldts.breakout.Position

class BallTest extends spock.lang.Specification{
    def "Teste Direção da Bola"(){
        given:
        def ball = new Ball()

        when:
        def x = ball.getDirX()
        def y = ball.getDirY()

        then:
        x == 1
        y ==1

        when:
        ball.setDirX(-1)
        ball.setDirY(-1)

        then:
        ball.getDirX() == -1
        ball.getDirY() == -1

    }

    def "Colisão entre Bola e Paddle"(){
        given:
        def ball = new Ball()

        when:
        ball.setDestroyedBrick(true)
        ball.hitPaddle()

        then:
        ball.getDirY() == -1
        ball.getDirX() == 1
        ball.getDestroyedBrick() == false
    }

    def "Colisão entre Bola e Brick"(){
        given:
        def ball = new Ball()

        when:
        ball.setDestroyedBrick(false)
        ball.setDirX(1)
        ball.setDirY(1)
        ball.hitBrick()

        then:
        ball.getDirX() == 1
        ball.getDirY() == -1
        ball.getDestroyedBrick() == true

        when:
        ball.setDirX(1)
        ball.setDirY(-1)
        ball.hitBrick()

        then:
        ball.getDirX() == 1
        ball.getDirY() == 1
    }

    def "Teste Movimento da Bola"(){
        given:
        def ball = new Ball()

        when: "A Bola está no canto superior esquerdo"
        ball.setPosition(new Position(Constants.BORDER_LEFT_X + 1,Constants.BORDER_TOP_Y + 1))
        ball.move()

        then:
        1 == ball.getDirX()
        1 == ball.getDirY()

        when:
        ball.setPosition(new Position(Constants.BORDER_LEFT_X, Constants.BORDER_TOP_Y))
        ball.move()
        then:
        -1 == ball.getDirX()
        -1 == ball.getDirY()

        when: "A bola está no canto inferior direito"
        ball.setDirX(1)
        ball.setDirY(1)
        ball.setPosition(new Position(Constants.BORDER_RIGHT_X - 1,Constants.BORDER_BOTTOM_Y - 1))
        ball.move()

        then:
        1 == ball.getDirX()
        1 == ball.getDirY()

        when:
        ball.setPosition(new Position(Constants.BORDER_RIGHT_X,Constants.BORDER_BOTTOM_Y))
        ball.move()

        then:
        -1 == ball.getDirX()
        -1 == ball.getDirY()
    }


}
