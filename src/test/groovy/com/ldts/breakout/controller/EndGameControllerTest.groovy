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

        def game = Mockito.mock(Game.class)
        Mockito.doCallRealMethod().when(game).setGameState(Mockito.any())
        Mockito.doCallRealMethod().when(game).getGameState()

        def command = Mockito.mock(MenuButtonCommand.class)
        def gameState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(gameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(gameState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(command).setNextState(Mockito.any())
        Mockito.doCallRealMethod().when(command).execute()

        gameState.setGame(game)
        command.setNextState(gameState)

        button1.setCommand(command)

        List<Button> buttons = Arrays.asList(button1)

        def endGameState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(endGameState).getButtons()
        Mockito.doCallRealMethod().when(endGameState).setButtons(Mockito.anyList())
        Mockito.doCallRealMethod().when(endGameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(endGameState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(endGameState).getGame()

        endGameState.setButtons(buttons)
        game.setGameState(endGameState)
        endGameState.setGame(game)

        def gui = Mockito.mock(GUI.class)
        def endGameController = new EndGameController(endGameState, gui, true)

        when:
        def action = GUI.ACTION.QUIT
        endGameController.keyPressed(action)

        then:
        endGameState.getGame().getGameState() == null

        when:
        endGameState.getGame().setGameState(endGameState)
        action = GUI.ACTION.CHOOSE
        endGameController.keyPressed(action)

        then:
        endGameState.getGame().getGameState() == gameState

    }
}

