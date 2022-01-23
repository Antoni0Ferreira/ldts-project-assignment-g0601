package com.ldts.breakout;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, FontFormatException {
        Game game = null;
        try {
            game = Game.getInstance();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        game.start();
    }
}