package com.ldts.breakout.model

class WallTest extends spock.lang.Specification {
    def "Teste às posições das walls"(){
        given:
        def wall1 = new Wall(1,2)
        def wall2 = new Wall(1,2)
        when:
        def wall3 = new Wall(1,1)
        then:
        wall1.getPosition() == wall3.getPosition()
        wall1.getPosition() != wall2.getPosition()
        wall3.getPosition() != wall2.getPosition()

    }
}
