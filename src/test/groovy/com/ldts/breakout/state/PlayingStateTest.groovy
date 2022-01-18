package com.ldts.breakout.state

import com.ldts.breakout.Game
import com.ldts.breakout.controller.PlayingController
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import org.mockito.Mockito

class PlayingStateTest extends spock.lang.Specification{

    def "Teste Start"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def playingState = new PlayingState(game,gui)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        playingState.start()

        then:
        Mockito.verify(keyBoardObserver,Mockito.times(1)).setListener(Mockito.any())
    }

    def "Teste Step"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def playingController = Mockito.mock(PlayingController.class)
        def playingState = new PlayingState(game,gui)
        playingState.setPlayingController(playingController)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        playingState.step(game)

        then:
        Mockito.verify(playingController,Mockito.times(1)).step(game)

    }
}
