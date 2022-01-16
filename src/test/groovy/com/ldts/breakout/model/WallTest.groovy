package com.ldts.breakout.model

class WallTest extends spock.lang.Specification {

    def "Teste das posições das walls"(){
        given:
        def wall1 = new Wall(new Position(1,1))
        def wall2 = new Wall(new Position(2,1))

        when:
        def wall3 = new Wall(new Position(1,1))

        then:
        wall1.getPosition() == wall3.getPosition()
        wall1.getPosition() != wall2.getPosition()
        wall3.getPosition() != wall2.getPosition()
    }
}
