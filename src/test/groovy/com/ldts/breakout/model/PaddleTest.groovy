package com.ldts.breakout.model

import com.ldts.breakout.Constants
import org.mockito.Mockito

class PaddleTest extends spock.lang.Specification{

    def "Teste à posição do Paddle"(){
        given:
        def paddle = Mockito.mock(Paddle.class)

        Mockito.doCallRealMethod().when(paddle).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(paddle).getPosition()

        when:
        paddle.setPosition(new Position(1,1))
        def position1 = new Position(1,1)
        def position2 = new Position(1,2)
        def position3 = new Position(2,1)
        def position4 = new Position(2,2)

        then:
        position1 == paddle.getPosition()
        position2 != paddle.getPosition()
        position3 != paddle.getPosition()
        position4 != paddle.getPosition()
    }

    def "Teste aos pontos do Paddle "(){
        given:
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y))

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
        def paddle = new Paddle(new Position(Constants.INIT_PADDLE_X,Constants.INIT_PADDLE_Y))

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
