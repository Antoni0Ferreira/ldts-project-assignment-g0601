package com.ldts.breakout

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType
import com.ldts.breakout.model.Paddle
import com.ldts.breakout.model.Position

class GameTest extends spock.lang.Specification{

    def "testing process key #1"() {
        given:
        def key = Mock(KeyStroke.class)
        def paddle = new Paddle()
        def arena = new OtherArena(paddle)
        def game = new Game(arena)

        key.getKeyType() >> KeyType.ArrowLeft

        when: "we want to move the paddle to the left"
        game.processKey(key)
        def newPosition = game.arena.paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X-1, Constants.INIT_PADDLE_Y)
    }

    def "testing process key #2"() {
        given:
        def key = Mock(KeyStroke.class)
        def paddle = new Paddle()
        def arena = new OtherArena(paddle)
        def game = new Game(arena)

        key.getKeyType() >> KeyType.ArrowRight

        when: "we want to move the paddle to the right"
        game.processKey(key)
        def newPosition = game.arena.paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X+1, Constants.INIT_PADDLE_Y)
    }

    def "testing process key #3"(){
        given:
        def key = Mock(KeyStroke.class)
        def paddle = new Paddle()
        def arena = new OtherArena(paddle)
        def game = new Game(arena)

        key.getKeyType() >> KeyType.ArrowDown // o método processKey apenas aceita input ArrowRight e ArrowLeft

        when:
        game.processKey(key)
        def newPosition = game.arena.paddle.getPosition()

        then:
        newPosition == new Position(Constants.INIT_PADDLE_X, Constants.INIT_PADDLE_Y)
    }
}