package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends DynamicEntity{

    private int lives;
    private int points = 0;

    public Player(int x, int y, Direction direction, int speed, int lives) {
        super(x, y, direction, speed);
        lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incrementPoints(int points){
      this.points += points;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffe118"));
        graphics.fillRectangle(new TerminalPosition(0, 3), new TerminalSize(1, 1), ' ');
    }
}
