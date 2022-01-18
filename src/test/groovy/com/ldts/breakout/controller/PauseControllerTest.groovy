package com.ldts.breakout.controller

import com.ldts.breakout.model.Button
import org.mockito.Mockito

class PauseControllerTest extends spock.lang.Specification{

    def "Teste ao getActiveButton"(){
        def button1 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button1).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button1).activate()
        Mockito.doCallRealMethod().when(button1).deactivate()
        Mockito.doCallRealMethod().when(button1).isActive()
        Mockito.doCallRealMethod().when(button1).getPosition()

        def button2 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button2).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button2).activate()
        Mockito.doCallRealMethod().when(button2).deactivate()
        Mockito.doCallRealMethod().when(button2).isActive()
        Mockito.doCallRealMethod().when(button2).getPosition()

        button1.activate()
        button2.deactivate()
        List<Button> buttons = Arrays.asList(button1,button2)
    }
}
