package com.ldts.breakout

import com.ldts.breakout.model.Button
import com.ldts.breakout.model.Position
import com.ldts.breakout.viewer.state.StateViewer
import org.mockito.Mockito

class StateViewerTest extends spock.lang.Specification{

    def "Teste ao getYActiveButton"(){
        given:
        def stateViewer = Mockito.mock(StateViewer.class)
        Mockito.doCallRealMethod().when(stateViewer).setButtons(Mockito.anyList())
        Mockito.doCallRealMethod().when(stateViewer).getYActiveButton()
        def button = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button).activate()
        Mockito.doCallRealMethod().when(button).deactivate()
        Mockito.doCallRealMethod().when(button).isActive()
        Mockito.doCallRealMethod().when(button).getPosition()
        button.setPosition(new  Position(2,2))
        button.activate()
        List<Button> buttons = Arrays.asList(button)
        stateViewer.setButtons(buttons as List<Button>)

        when:
        def y = stateViewer.getYActiveButton()

        then:
        y == 2

        when:
        button.deactivate()
        buttons = Arrays.asList(button)
        stateViewer.setButtons(buttons as List<Button>)
        y = stateViewer.getYActiveButton()

        then:
        -1 == y
    }
}
