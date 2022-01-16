package com.ldts.breakout.gui

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.ldts.breakout.Constants
import com.ldts.breakout.Position
import org.mockito.Mockito

class LanternaTest extends spock.lang.Specification{

    LanternaGUI gui
    TextGraphics textGraphics

    def setup(){
        this.textGraphics = Mockito.mock(TextGraphics.class)
        this.gui = new LanternaGUI()
    }

    def "Teste desenho da Bola"(){
        given:
        def position = new Position(5,5)
        def color = "#FFFFFF"
        when:
        gui.drawBall(position)

        then:
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color))
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(),position.getY(),"@")

    }

    def "Teste desenho do Paddle"(){
        given:
        def position = new Position(5,5)
        def color = "#FF7000"
        when:
        gui.drawPaddle()
        then:
        Mockito.verify(textGraphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color))
        Mockito.verify(textGraphics,Mockito.times(1)).drawRectangle(position, new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-')
    }

    def "Teste desenho do Wall"(){
        given:
        def position1 = new Position(0,0)
        def position2 = new Position(0,4)
        def position3 = new Position(0,5)
        def position4 = new Position(0,6)
        def position5 = new Position(0,7)
        def position6 = new Position(0,8)
        def position7 = new Position(0,Constants.INIT_PADDLE_Y)

        def color1 = "#FFFFFF"
        def color2 = "#FF0000"
        def color3 = "#FF8700"
        def color4 = "#FFC100"
        def color5 = "#CDFF00"
        def color6 = "#008FFF"
        def color7 = "#FF7000"

        when:
        gui.drawWall(position1)
        gui.drawWall(position2)
        gui.drawWall(position3)
        gui.drawWall(position4)
        gui.drawWall(position5)
        gui.drawWall(position6)
        gui.drawWall(position7)

        then:

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color1))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position1.getX(),position1.getY()," ")

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color2))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position2.getX(),position2.getY()," ")

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color3))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position3.getX(),position3.getY()," ")

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color4))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position4.getX(),position4.getY()," ")

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color5))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position5.getX(),position5.getY()," ")

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color6))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position6.getX(),position6.getY()," ")

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color7))
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD)
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position7.getX(),position7.getY()," ")
    }

    def "Teste desenho dos Bricks"(){
        given:
        def position1 = new Position(5,4)
        def position2 = new Position(5,5)
        def position3 = new Position(5,6)
        def position4 = new Position(5,7)
        def position5 = new Position(5,8)
        def color1 = "#FF0000"
        def color2 = "#FF8700"
        def color3 = "#FFC100"
        def color4 = "#CDFF00"
        def color5 = "#008FFF"

        when:
        gui.drawBrick(position1)
        gui.drawBrick(position2)
        gui.drawBrick(position3)
        gui.drawBrick(position4)
        gui.drawBrick(position5)

        then:
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color1))
        Mockito.verify(textGraphics,Mockito.times(1)).drawRectangle(position1, new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-')
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color2))
        Mockito.verify(textGraphics,Mockito.times(1)).drawRectangle(position2, new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-')
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color3))
        Mockito.verify(textGraphics,Mockito.times(1)).drawRectangle(position3, new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-')
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color4))
        Mockito.verify(textGraphics,Mockito.times(1)).drawRectangle(position4, new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-')
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color5))
        Mockito.verify(textGraphics,Mockito.times(1)).drawRectangle(position5, new TerminalSize(Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT),'-')
        
    }

}
