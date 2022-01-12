package com.ldts.breakout;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game(30);
        game.start();

    }
}
