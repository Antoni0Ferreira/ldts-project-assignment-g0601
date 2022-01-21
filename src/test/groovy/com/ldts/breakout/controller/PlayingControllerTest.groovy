package com.ldts.breakout.controller

import com.ldts.breakout.Game
import com.ldts.breakout.gui.GUI
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.model.command.MenuButtonCommand
import com.ldts.breakout.state.GameState
import com.ldts.breakout.model.arena.Arena
import com.ldts.breakout.state.KeyBoardListener
import com.ldts.breakout.state.PauseState
import org.mockito.Mockito

class PlayingControllerTest extends spock.lang.Specification{

    def "Teste ao keyPressed"(){
        given:
        def keyBoardListener = new KeyBoardObserver()

        def game = Mockito.mock(Game.class)
        Mockito.doCallRealMethod().when(game).setGameState(Mockito.any())
        Mockito.doCallRealMethod().when(game).getGameState()
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardListener)

        def command = Mockito.mock(MenuButtonCommand.class)
        def gameState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(gameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(gameState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(command).setNextState(Mockito.any())
        Mockito.doCallRealMethod().when(command).execute()

        gameState.setGame(game)
        command.setNextState(gameState)

        def playingState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(playingState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(playingState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(playingState).getGame()

        game.setGameState(playingState)
        playingState.setGame(game)

        def gui = Mockito.mock(GUI.class)
        def arena = Mockito.mock(Arena.class)
        def playingController = new PlayingController(playingState,gui,arena)

        def action = GUI.ACTION.QUIT

        when:
        playingController.keyPressed(action)

        then:
        playingState.getGame().getGameState().getClass() == PauseState.class

    }

}
