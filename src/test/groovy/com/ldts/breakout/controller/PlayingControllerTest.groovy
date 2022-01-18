package com.ldts.breakout.controller

import com.ldts.breakout.Game
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.state.GameState
import com.ldts.breakout.state.MenuState
import com.ldts.breakout.state.PlayingState
import org.mockito.Mockito

class PlayingControllerTest extends spock.lang.Specification{
    def playingController

    def "Teste ao keyPressed"(){
        given:
        def gameState = Mockito.mock(GameState.class)
        def gui = new LanternaGUI()
        def arena = Mockito.mock(Arena.class)
        this.playingController = new PlayingController(gameState,gui,arena)
        def action = GUI.ACTION.QUIT

        when:
        playingController.keyPressed(action)

        then:
        playingController.gameState == new PauseState(gameState.getGame(),gui,gameState)


    }

}
