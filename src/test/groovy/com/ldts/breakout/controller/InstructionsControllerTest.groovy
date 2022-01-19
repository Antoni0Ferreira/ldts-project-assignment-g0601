package com.ldts.breakout.controller

import com.ldts.breakout.Game
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.model.Button
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.command.Command
import com.ldts.breakout.model.command.MenuButtonCommand
import com.ldts.breakout.state.GameState
import org.mockito.Mockito

class InstructionsControllerTest extends spock.lang.Specification {

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

        def instructionsState = Mockito.mock(GameState.class)
        Mockito.doCallRealMethod().when(instructionsState).getButtons()
        Mockito.doCallRealMethod().when(instructionsState).setButtons(Mockito.anyList())

        instructionsState.setButtons(buttons)

        def gui = Mockito.mock(GUI.class)
        def instructionsController = new InstructionsController(instructionsState,gui)

        when:
        def i = instructionsController.getActiveButton()

        then:
        0 == i
    }

    def "Teste ao keyPressed"(){
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

        def instructionsState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(instructionsState).getButtons()
        Mockito.doCallRealMethod().when(instructionsState).setButtons(Mockito.anyList())
        Mockito.doCallRealMethod().when(instructionsState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(instructionsState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(instructionsState).getGame()

        instructionsState.setButtons(buttons)
        game.setGameState(instructionsState)
        instructionsState.setGame(game)

        def gui = Mockito.mock(GUI.class)
        def instructionsController = new InstructionsController(instructionsState, gui,)

        when:
        def action = GUI.ACTION.QUIT
        instructionsController.keyPressed(action)

        then:
        instructionsState.getGame().getGameState() == null

        when:
        instructionsState.getGame().setGameState(instructionsState)
        action = GUI.ACTION.CHOOSE
        instructionsController.keyPressed(action)

        then:
        instructionsState.getGame().getGameState() == gameState
    }
}
