package com.ldts.breakout.state
import com.ldts.breakout.Game
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import org.mockito.Mockito

class MenuStateTest extends spock.lang.Specification{

    def "Teste Start"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def menuState = new MenuState(game,gui)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        menuState.start()

        then:
        Mockito.verify(keyBoardObserver,Mockito.times(1)).setListener(Mockito.any())
    }

    def "Teste Step"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)
        def menuState = new MenuState(game,gui)

        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)

        when:
        menuState.step(game)

        then:
        1 == 1


    }


}
