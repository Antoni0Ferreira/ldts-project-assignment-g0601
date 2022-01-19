package com.ldts.breakout.state

import com.ldts.breakout.Game
import com.ldts.breakout.controller.InstructionsController
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import org.mockito.Mockito

class InstructionsStateTest extends spock.lang.Specification{

    def "Teste Start"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(GUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def instructionsState = new InstructionsState(game,gui)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        instructionsState.start()

        then:
        Mockito.verify(keyBoardObserver,Mockito.times(1)).setListener(Mockito.any())
    }

    def "Teste Step"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def instructionsController = Mockito.mock(InstructionsController.class)
        def won = true

        def endGameState = new EndGameState(game,gui,won)
        endGameState.setEndGameController(instructionsController)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        endGameState.step(game)

        then:
        Mockito.verify(instructionsController,Mockito.times(1)).step()
    }
}
