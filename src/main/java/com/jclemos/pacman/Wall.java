package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Wall extends  MapEntity{

    public Wall(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#0071bc"));
        graphics.fillRectangle(new TerminalPosition(x, y), new TerminalSize(1, 1), ' ');
    }
}