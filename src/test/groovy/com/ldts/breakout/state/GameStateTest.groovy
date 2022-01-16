package com.ldts.breakout.state
import com.ldts.breakout.Game
import com.ldts.breakout.model.Button
import org.mockito.Mockito

class GameStateTest extends spock.lang.Specification {
    def "Teste de mudança de state"(){
        given:
        def game = new Game()
        def buttons = Arrays.asList()
        def playingState = new PlayingState(game, buttons as List<Button>)

        def menuState = new MenuState(game, buttons as List<Button>)
        def gameState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(gameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(gameState).setGame(Mockito.any())
        Mockito.when(gameState.getGame()).thenReturn(game)
        Mockito.when(gameState.getButtons()).thenReturn(buttons)

        when:
        gameState.setGame(game)
        gameState.changeState(menuState)

        then:
        game.getGameState() == menuState

        when:
        gameState.changeState(playingState)

        then:
        game.getGameState() == playingState
    }


    def "Teste de getGame" (){

    }

    def "Teste de getButtons"(){

    }
}
