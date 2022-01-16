package com.ldts.breakout.model

import com.ldts.breakout.Brick
import com.ldts.breakout.Position

class BrickTest extends spock.lang.Specification{


    def "Teste para verificar se um Brick está destruído"(){
        given:
        def brick = new Brick(new Position(1,1))

        when:
        def isBrickDestroyed = brick.isDestroyed()

        then:
        isBrickDestroyed == false

        when:
        brick.setDestroyed(true)
        isBrickDestroyed =brick.isDestroyed()

        then:
        isBrickDestroyed == true

    }

    def "Teste aos pontos do brick"(){
        given:
        def brick1 = new Brick( new Position(1,4))
        def brick2 = new Brick( new Position(1,5))
        def brick3 = new Brick( new Position(1,6))
        def brick4 = new Brick( new Position(1,7))
        def brick5 = new Brick( new Position(1,8))

        when:
        def points1 = brick1.getPoints()
        def points2 = brick2.getPoints()
        def points3 = brick3.getPoints()
        def points4 = brick4.getPoints()
        def points5 = brick5.getPoints()

        then:
        points1 == 50
        points2 == 20
        points3 == 10
        points4 == 5
        points5 == 1
    }


}
