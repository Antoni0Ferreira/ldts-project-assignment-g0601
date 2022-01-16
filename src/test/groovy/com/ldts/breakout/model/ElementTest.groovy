package com.ldts.breakout.model

import com.ldts.breakout.Element
import com.ldts.breakout.Position
import org.mockito.Mockito

class ElementTest extends spock.lang.Specification{

    def "Teste à Position do Element"(){
        given:
        def element = Mockito.mock(Element.class)

        Mockito.doCallRealMethod().when(element).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(element).getPosition()

        when:
        element.setPosition(new Position(1,1))
        def position1 = new Position(1,1)
        def position2 = new Position(1,2)
        def position3 = new Position(2,1)
        def position4 = new Position(2,2)

        then:
        position1 == element.getPosition()
        position2 != element.getPosition()
        position3 != element.getPosition()
        position4 != element.getPosition()
    }

}
