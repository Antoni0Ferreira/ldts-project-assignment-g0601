package com.ldts.breakout.controller

import com.ldts.breakout.Game
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.model.Position
import com.ldts.breakout.model.command.MenuButtonCommand
import com.ldts.breakout.state.GameState
import com.ldts.breakout.state.MenuState
import org.mockito.Mock
import org.mockito.Mockito

import com.ldts.breakout.model.Button

class MenuControllerTest extends spock.lang.Specification{

    def "Teste ao getActiveButton"(){

        given:
        def gui = Mockito.mock(GUI.class)
        def button1 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button1).activate()
        Mockito.doCallRealMethod().when(button1).deactivate()
        Mockito.doCallRealMethod().when(button1).isActive()
        button1.deactivate()

        def button2 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button2).activate()
        Mockito.doCallRealMethod().when(button2).deactivate()
        Mockito.doCallRealMethod().when(button2).isActive()
        button2.activate()

        def buttons = Arrays.asList(button1, button2)

        def gameState = Mockito.mock(GameState.class)
        Mockito.doCallRealMethod().when(gameState).setButtons(Mockito.anyList())
        Mockito.doCallRealMethod().when(gameState).getButtons()
        gameState.setButtons(buttons)

        def menuController = new MenuController(gameState, gui)

        when:
        int index = menuController.getActiveButton()

        then:
        index == 1

    }

    def "Teste ao keyPressed"(){
        given:
        def button1 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button1).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button1).activate()
        Mockito.doCallRealMethod().when(button1).deactivate()
        Mockito.doCallRealMethod().when(button1).isActive()
        Mockito.doCallRealMethod().when(button1).getPosition()
        Mockito.doCallRealMethod().when(button1).setCommand(Mockito.any())
        Mockito.doCallRealMethod().when(button1).getCommand()

        def button2 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button2).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button2).activate()
        Mockito.doCallRealMethod().when(button2).deactivate()
        Mockito.doCallRealMethod().when(button2).isActive()
        Mockito.doCallRealMethod().when(button2).getPosition()
        Mockito.doCallRealMethod().when(button2).setCommand(Mockito.any())
        Mockito.doCallRealMethod().when(button2).getCommand()

        def button3 = Mockito.mock(Button.class)
        Mockito.doCallRealMethod().when(button3).setPosition(Mockito.any())
        Mockito.doCallRealMethod().when(button3).activate()
        Mockito.doCallRealMethod().when(button3).deactivate()
        Mockito.doCallRealMethod().when(button3).isActive()
        Mockito.doCallRealMethod().when(button3).getPosition()
        Mockito.doCallRealMethod().when(button3).setCommand(Mockito.any())
        Mockito.doCallRealMethod().when(button3).getCommand()

        button1.setPosition(new Position(1,1))
        button2.setPosition(new Position(1,2))
        button3.setPosition(new Position(1,3))
        button1.activate()
        button2.deactivate()
        button3.deactivate()

        List<Button> buttons = Arrays.asList(button1,button2,button3)

        def menuState = Mockito.mock(GameState.class)
        Mockito.doCallRealMethod().when(menuState).getButtons()
        Mockito.doCallRealMethod().when(menuState).setButtons(Mockito.anyList())
        Mockito.doCallRealMethod().when(menuState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(menuState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(menuState).getGame()

        def game = Mockito.mock(Game.class)
        Mockito.doCallRealMethod().when(game).setGameState(Mockito.any())


        menuState.setButtons(buttons)
        game.setGameState(menuState)
        menuState.setGame(game)

        def gui = Mockito.mock(GUI.class)
        def menuController = new MenuController(menuState,gui)

        def command = Mockito.mock(MenuButtonCommand.class)
        Mockito.doCallRealMethod().when(command).execute()

        button1.setCommand(command)
        button2.setCommand(command)
        button3.setCommand(command)

        when:
        def action = GUI.ACTION.DOWN
        menuController.keyPressed(action)

        then:
        !button1.isActive()
        button2.isActive()
        !button3.isActive()

        when:
        menuController.keyPressed(action)

        then:
        !button1.isActive()
        !button2.isActive()
        button3.isActive()

        when:
        menuController.keyPressed(action)

        then:
        button1.isActive()
        !button2.isActive()
        !button3.isActive()

        when:
        action = GUI.ACTION.UP
        menuController.keyPressed(action)

        then:
        !button1.isActive()
        !button2.isActive()
        button3.isActive()

        when:
        menuController.keyPressed(action)

        then:
        !button1.isActive()
        button2.isActive()
        !button3.isActive()

        when:
        action = GUI.ACTION.QUIT
        menuController.keyPressed(action)

        then:
        menuState.getGame().getGameState() == null

        when:
        action = GUI.ACTION.CHOOSE
        menuController.keyPressed(action)

        then:
        button2.getCommand().execute()

    }
}
