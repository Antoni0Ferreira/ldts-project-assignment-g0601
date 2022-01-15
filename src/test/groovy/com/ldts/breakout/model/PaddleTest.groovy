package com.ldts.breakout.model

import com.ldts.breakout.Constants
import org.mockito.Mockito

class PaddleTest extends spock.lang.Specification{

    def "Teste aos pontos do Paddle "(){
        given:
        def paddle = new Paddle()

        when:
        def newPoints = 0;

        then:
        newPoints == paddle.getPoints()

        when:
        paddle.updatePoints(3)
        paddle.updatePoints(1)

        then:
        newPoints != paddle.getPoints()
        4 == paddle.getPoints()
    }

    def "Teste às vidas do Paddle"(){
        given:
        def paddle = new Paddle()

        when:
        def newLives = 3;

        then:
        0 <= paddle.getLives()
        newLives == paddle.getLives()

        when:
        paddle.updateLives()
        paddle.updateLives()

        then:
        newLives != paddle.getLives()
        1 == paddle.getLives()
        0 <= paddle.getLives()
    }

}
