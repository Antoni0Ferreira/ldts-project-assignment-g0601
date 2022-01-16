package com.ldts.breakout.model

import com.ldts.breakout.model.command.MenuButtonCommand
import org.mockito.Mockito
import com.ldts.breakout.model.command.Command

class ButtonTest extends spock.lang.Specification {


    def "Teste Ativação dos Buttons"(){
        given:
        def position = Mockito.mock(Position.class)
        def command = Mockito.mock(MenuButtonCommand.class)
        def text = "Button"
        def button = new Button(position,text,command,"#FFFFFF")

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
       given:
       def position = Mockito.mock(Position.class)
       def command = Mockito.mock(MenuButtonCommand.class)
       def text = "Button"
       def button = new Button(position,text,command,"#FFFFFF")

       when:
       String s1 = "Button"

       then:
       s1 == button.getText()

    }
}
