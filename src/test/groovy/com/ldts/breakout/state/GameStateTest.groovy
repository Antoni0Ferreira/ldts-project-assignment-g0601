package com.ldts.breakout.state
import com.ldts.breakout.Game
import com.ldts.breakout.model.Button
import org.mockito.Mockito;

class GameStateTest extends spock.lang.Specification {
    def "Teste de mudança de state"(){
        given:
        def game = new Game()
        def gameState = PlayingState(game)
        def buttons = Arrays.asList(new Button(null,"",null,""))

        GameState newState = new GameState(game,buttons) {
            @Override
            void start() {

            }

            @Override
            void step(Game otherGame, long time) throws IOException {}
        }

        Mockito.when(gameState.getGame()).thenReturn(game)
        //Mockito.doCallRealMethod().when(gameState).changeState(newState)

        when:
        gameState.changeState(newState)

        then:
        Mockito.verify(game,Mockito.times(1)).setGameState(newState)


    }

    def "Teste de step"() {

    }

    def "Teste de getGame" (){

    }

    def "Teste de getButtons"(){

    }
}
