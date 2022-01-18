package com.ldts.breakout.model

class PositionTest extends spock.lang.Specification{

    def "Teste ao Construtor do Position"(){
        given:
        def position = new Position(1,1)
        when:
        def x = 1
        def y = 1
        then:
        x == position.getX();
        y == position.getY();
    }

    def "Teste ao setPosition()"(){
        given:
        def x = 2
        def y = 1
        def position = new Position(0,0)
        when:
        position.setX(x)
        position.setY(y)
        then:
        x == position.getX()
        y == position.getY()
    }

    def "Teste ao Position Equals"(){
        given:
        def position1 = new Position(1,2)
        def position3 = new Position(3,3)
        when:
        def position2 = new Position(1,2)
        then:
        position1 == position2
        position3 != position1
        position3 != position2
    }
}
