package com.ldts.breakout.state

import com.ldts.breakout.Game
import com.ldts.breakout.controller.PauseController
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import org.mockito.Mockito

class PauseStateTest extends spock.lang.Specification{

    def "Teste Start"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def previousState = Mockito.mock(GameState.class)
        def pauseState = new PauseState(game,gui,previousState)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        pauseState.start()

        then:
        Mockito.verify(keyBoardObserver,Mockito.times(1)).setListener(Mockito.any())
    }

    def "Teste Step"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def pauseController = Mockito.mock(PauseController.class)
        def pauseState = Mockito.mock(PauseState.class)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)
        Mockito.when(pauseState.getPauseController()).thenReturn(pauseController)

        when:
        pauseState.step(game)

        then:
        Mockito.verify(pauseController,Mockito.times(1)).step()

    }
}
