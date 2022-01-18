package com.ldts.breakout.controller

import com.ldts.breakout.model.Button
import com.ldts.breakout.model.Position
import org.mockito.Mockito

class EndGameControllerTest extends spock.lang.Specification{
    def endGameController

    def "Teste ao getActiveButton"(){
        given:
        def button1 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button1).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button1).activate()
        Mockito.doCallRealMethod().when(button1).deactivate()
        Mockito.doCallRealMethod().when(button1).isActive()
        Mockito.doCallRealMethod().when(button1).getPosition()


        button1.setPosition(new Position(1,1))
        button1.activate()

        List<Button> buttons = Arrays.asList(button1)

        def endGameState = Mockito.mock(endGameState.class)
        Mockito.doCallRealMethod().when(endGameState).getButtons()
        Mockito.doCallRealMethod().when(endGameState).setButtons(Mockito.anyList())

        endGameState.setButtons(buttons)

        def gui = Mockito.mock(GUI.class)
        def endGameController = new EndGameController(endGameState,gui,true)

        when:
        def i = endGameController.getActiveButton()

        then:
        i == 0

    }

    def "Teste ao keyPressed"() {
        given:
        def button1 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button1).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button1).activate()
        Mockito.doCallRealMethod().when(button1).deactivate()
        Mockito.doCallRealMethod().when(button1).isActive()
        Mockito.doCallRealMethod().when(button1).getPosition()

        button1.setPosition(new Position(1, 1))

        button1.activate()

        List<Button> buttons = Arrays.asList(button1)

        def endGameState = Mockito.mock(endGameState.class)
        Mockito.doCallRealMethod().when(endGameState).getButtons()
        Mockito.doCallRealMethod().when(endGameState).setButtons(Mockito.anyList())

        endGameState.setButtons(buttons)

        def gui = Mockito.mock(GUI.class)
        def endGameController = new EndGameController(endGameState, gui, true)

        when:
        def action = GUI.ACTION.QUIT
        endGameController.keyPressed(action)

        then:
        endGameController.getGameState() == null

        when:
        action = GUI.ACTION.CHOOSE
        endGameController.keyPressed(action)

        then:
        button1.getCommand().execute() == true

    }
}

