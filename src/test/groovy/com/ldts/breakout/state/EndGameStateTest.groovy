package com.ldts.breakout.state

import com.ldts.breakout.Game
import com.ldts.breakout.controller.EndGameController
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.model.command.Command
import org.mockito.Mockito

class EndGameStateTest extends spock.lang.Specification{

    def "Teste Start"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(GUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def won = Mockito.anyBoolean()
        def endGameState = new EndGameState(game,gui,won)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        endGameState.start()

        then:
        Mockito.verify(keyBoardObserver,Mockito.times(1)).setListener(Mockito.any())
    }

    def "Teste Step()"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def endGameController = Mockito.mock(EndGameController.class)
        def won = Mockito.anyBoolean()

        def endGameState = new EndGameState(game,gui,won)
        endGameState.setEndGameController(endGameController)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        endGameState.step(game)

        then:
        Mockito.verify(endGameController,Mockito.times(1)).step()
    }
}
