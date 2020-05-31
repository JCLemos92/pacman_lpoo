package com.jclemos.pacman;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Pellet extends MapEntity{
    private int points;

    public Pellet(int x, int y, int points) {
        super(x, y);
        points = points;
    }

    public void draw(Screen screen, char symbol){
        screen.setCharacter(x, y, new TextCharacter(symbol));
    }
}
