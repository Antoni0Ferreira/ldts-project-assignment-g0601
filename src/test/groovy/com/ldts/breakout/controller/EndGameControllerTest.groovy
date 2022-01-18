package com.ldts.breakout.controller

import com.ldts.breakout.Game
import com.ldts.breakout.model.Button
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.command.Command
import com.ldts.breakout.model.command.MenuButtonCommand
import com.ldts.breakout.state.EndGameState
import com.ldts.breakout.state.GameState
import com.ldts.breakout.gui.GUI

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

        def endGameState = Mockito.mock(GameState.class)
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
        Mockito.doCallRealMethod().when(button1).setCommand(Mockito.any(Command))
        Mockito.doCallRealMethod().when(button1).getCommand()

        button1.setPosition(new Position(1, 1))
        button1.activate()

        List<Button> buttons = Arrays.asList(button1)

        def endGameState = Mockito.mock(GameState.class)
        Mockito.doCallRealMethod().when(endGameState).getButtons()
        Mockito.doCallRealMethod().when(endGameState).setButtons(Mockito.anyList())

        def game = Mockito.mock(Game.class)
        Mockito.doCallRealMethod().when(game).setGameState(Mockito.any())

        endGameState.setButtons(buttons)
        endGameState.setGame(game)

        def gui = Mockito.mock(GUI.class)
        def endGameController = new EndGameController(endGameState, gui, true)

        def command = Mockito.mock(MenuButtonCommand.class)
        Mockito.doCallRealMethod().when(command).execute()

        button1.setCommand(command)

        when:
        def action = GUI.ACTION.QUIT
        endGameController.keyPressed(action)

        then:
        game.getGameState() == null

        when:
        action = GUI.ACTION.CHOOSE
        endGameController.keyPressed(action)

        then:
        button1.getCommand().execute() == true

    }
}

