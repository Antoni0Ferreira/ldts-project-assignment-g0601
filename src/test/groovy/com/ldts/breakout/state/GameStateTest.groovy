package com.ldts.breakout.state
import com.ldts.breakout.Game
import com.ldts.breakout.model.Button
import org.mockito.Mockito

class GameStateTest extends spock.lang.Specification {
    def "Teste de mudança de State"(){
        given:
        def game = new Game()
        def buttons = Arrays.asList()
        def playingState = new PlayingState(game, buttons as List<Button>)

        def menuState = new MenuState(game, buttons as List<Button>)
        def gameState = Mockito.mock(GameState.class)

        Mockito.doCallRealMethod().when(gameState).changeState(Mockito.any())
        Mockito.doCallRealMethod().when(gameState).setGame(Mockito.any())
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
