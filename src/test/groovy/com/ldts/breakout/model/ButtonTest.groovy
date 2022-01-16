package com.ldts.breakout.model

import org.mockito.Mockito
import com.ldts.breakout.model.command.Command

class ButtonTest extends spock.lang.Specification {

    def setup(){
        def position = Mockito.mock(Position.class)
        def command = Mockito.mock(Command.class)
        def text = "Button"
        def button = new Button(position,text,command,"#FFFFFF")
    }

    def "Teste Ativação dos Buttons"(){
        
        when:
        def buttonNotActivated = false
        def buttonActivated = true

        then:
        buttonActivated != button.isActive()
        buttonNotActivated == button.isActive()

        when:
        button.activate()

        then:
        buttonNotActivated != button.isActive()
        buttonActivated == button.isActive()

        when:
        button.deactivate()

        then:
        buttonActivated != button.isActive()
        buttonNotActivated == button.isActive()
    }

   def "Teste Texto dos Buttons" (){

        when:
        String s1 = "Button"

        then:
        s1 == button.getText()

    }
}
