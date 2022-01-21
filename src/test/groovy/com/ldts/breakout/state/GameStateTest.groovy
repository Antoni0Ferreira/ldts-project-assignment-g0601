package com.ldts.breakout.state
import com.ldts.breakout.Game
import com.ldts.breakout.gui.KeyBoardObserver
import com.ldts.breakout.gui.LanternaGUI
import com.ldts.breakout.model.Button
import org.mockito.Mockito

class GameStateTest extends spock.lang.Specification {

    def "Teste de mudança de State"(){
        given:
        def game = Mockito.mock(Game.class)
        def gui = Mockito.mock(LanternaGUI.class)
        def playingState = new PlayingState(game,gui)

        def menuState = new MenuState(game,gui)
        def gameState = Mockito.mock(GameState.class)
        def keyBoardObserver = Mockito.mock(KeyBoardObserver.class)

        Mockito.doCallRealMethod().when(gameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(gameState).setGame(Mockito.any())
        Mockito.doCallRealMethod().when(game).setGameState(Mockito.any())
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver)
        Mockito.doCallRealMethod().when(game).getGameState()

        gameState.setGame(game)

        when:
        gameState.changeState(menuState)

        then:
        game.getGameState() == menuState

        when:
        gameState.changeState(playingState)

        then:
        game.getGameState() == playingState
    }

    def "Teste Getter do Game"(){
        given:
        def gameState = Mockito.mock(GameState.class)
        def game = Mockito.mock(Game.class)

        Mockito.when(gameState.getGame()).thenReturn(game)

        when:
        Game newGame = gameState.getGame()

        then:
        newGame == game
    }

    def "Teste Getter dos Buttons"(){
        given:
        def gameState = Mockito.mock(GameState.class)
        def buttons = Arrays.asList(new Button(null,"",null,""))

        Mockito.when(gameState.getButtons()).thenReturn(buttons)

        when:
        List<Button> newButtons = gameState.getButtons()

        then:
        newButtons == buttons
    }
}
