package com.ldts.breakout.controller;

import com.ldts.breakout.model.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena){super(arena);}
}
