package com.ldts.breakout.controller

import com.ldts.breakout.Game
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.model.Button
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.command.Command
import com.ldts.breakout.state.GameState
import com.ldts.breakout.model.command.MenuButtonCommand
import org.mockito.Mockito

class PauseControllerTest extends spock.lang.Specification{

    def "Teste ao getActiveButton"(){

        given:
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

        button1.setPosition(new Position(1,1))
        button2.setPosition(new Position(1,2))
        button1.activate()
        button2.deactivate()

        List<Button> buttons = Arrays.asList(button1,button2)

        def pauseState = Mockito.mock(GameState.class)
        Mockito.doCallRealMethod().when(pauseState).getButtons()
        Mockito.doCallRealMethod().when(pauseState).setButtons(Mockito.anyList())

        pauseState.setButtons(buttons)

        def gui = Mockito.mock(GUI.class)
        def pauseController = new PauseController(pauseState,gui)

        when:
        def i = pauseController.getActiveButton()

        then:
        i == 0
    }

    def "Teste ao keyPressed "() {
        given:
        def button1 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button1).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button1).activate()
        Mockito.doCallRealMethod().when(button1).deactivate()
        Mockito.doCallRealMethod().when(button1).isActive()
        Mockito.doCallRealMethod().when(button1).getPosition()
        Mockito.doCallRealMethod().when(button1).setCommand(Mockito.any(Command))
        Mockito.doCallRealMethod().when(button1).getCommand()

        def button2 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button2).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button2).activate()
        Mockito.doCallRealMethod().when(button2).deactivate()
        Mockito.doCallRealMethod().when(button2).isActive()
        Mockito.doCallRealMethod().when(button2).getPosition()
        Mockito.doCallRealMethod().when(button2).setCommand(Mockito.any(Command))
        Mockito.doCallRealMethod().when(button2).getCommand()

        button1.setPosition(new Position(1,1))
        button2.setPosition(new Position(1,2))
        button1.activate()
        button2.deactivate()

        List<Button> buttons = Arrays.asList(button1,button2)

        def pauseState = Mockito.mock(GameState.class)
        Mockito.doCallRealMethod().when(pauseState).getButtons()
        Mockito.doCallRealMethod().when(pauseState).setButtons(Mockito.anyList())
        Mockito.doCallRealMethod().when(pauseState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(pauseState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(pauseState).getGame()

        def game = Mockito.mock(Game.class)
        Mockito.doCallRealMethod().when(game).setGameState(Mockito.any())

        pauseState.setButtons(buttons)
        game.setGameState(pauseState)
        pauseState.setGame(game)

        def gui = Mockito.mock(GUI.class)
        def pauseController = new PauseController(pauseState,gui)

        def command = Mockito.mock(MenuButtonCommand.class)
        Mockito.doCallRealMethod().when(command).execute()

        button1.setCommand(command)
        button2.setCommand(command)

        when:
        def action = GUI.ACTION.DOWN
        pauseController.keyPressed(action)

        then:
        !button1.isActive()
        button2.isActive()

        when:
        action = GUI.ACTION.UP
        pauseController.keyPressed(action)

        then:
        button1.isActive()
        !button2.isActive()

        when:
        action = GUI.ACTION.QUIT
        pauseController.keyPressed(action)

        then:
        pauseState.getGame().getGameState() == null

        when:
        action = GUI.ACTION.CHOOSE
        pauseController.keyPressed(action)

        then:
        button1.getCommand().execute()

    }
}
