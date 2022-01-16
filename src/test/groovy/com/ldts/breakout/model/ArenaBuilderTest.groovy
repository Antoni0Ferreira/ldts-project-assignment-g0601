package com.ldts.breakout.model

import com.ldts.breakout.Constants
import com.ldts.breakout.Ball
import com.ldts.breakout.Paddle
import com.ldts.breakout.arena.ArenaBuilder
import org.mockito.Mockito

class ArenaBuilderTest extends spock.lang.Specification{
    def "Teste do número de Bricks"(){
        given:
        def arena = new ArenaBuilder()
        when:
        def array = arena.createBricks()
        then:
        array.size() == 35

    }

    def "Teste do número de Walls"(){
        given:
        def arena = new ArenaBuilder()
        when:
        def array = arena.createWalls()
        then:
        array.size() == (Constants.HEIGHT) * 2 + (Constants.WIDTH ) * 2
    }

    def "Teste da criação da Bola e do Paddle"(){
        given:
        def arenaBuilder = Mockito.mock(ArenaBuilder.class)

        Mockito.doCallRealMethod().when(arenaBuilder).createBall()
        Mockito.doCallRealMethod().when(arenaBuilder).createPaddle()

        when:
        def ball = new Ball();
        def arenaBall = arenaBuilder.createBall()

        def paddle = new Paddle()
        def arenaPaddle = arenaBuilder.createPaddle()

        then:
        ball.getPosition() == arenaBall.getPosition()
        paddle.getPosition() == arenaPaddle.getPosition()

    }
}
