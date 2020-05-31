package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Maze {
    private char[][] mazeArray = {{'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}};

    public void draw(TextGraphics graphics){
        int mazeRowIndex = 0;
        int mazeColumnIndex = 0;

        for (char[] elementArray : mazeArray) {
            mazeRowIndex++;

            for(char element : elementArray) {
                mazeColumnIndex++;

                if (element == 'W') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#0071bc"));
                    graphics.fillRectangle(new TerminalPosition(mazeColumnIndex, mazeRowIndex), new TerminalSize(1, 1), ' ');
                }
            }
        }
    }
}
