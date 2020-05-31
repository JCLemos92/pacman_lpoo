package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class Maze {

    private static final char WALL_SYMBOL = 'W';
    private static final char EMPTY_WALL_SYMBOL = 'E';
    

    private char[][] mazeArray = new char[][]{
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
            {'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
            {'E', 'E', 'E', 'E', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W'},
            {' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W'},
            {'E', 'E', 'E', 'E', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
            {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
            {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
    };

    public boolean mazeCollision(int x, int y){
        if(mazeArray[y][x] != ' '){
            return true;
        } else {
            return false;
        }
    }

    public void draw(TextGraphics graphics){
        int mazeRowIndex = 0;
        int mazeColumnIndex = 0;

        for (char[] elementArray : mazeArray) {
            for(char element : elementArray) {
                if (element == 'W') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#1c75bb"));
                    graphics.fillRectangle(new TerminalPosition(mazeColumnIndex, mazeRowIndex), new TerminalSize(1, 1), ' ');
                }
                mazeColumnIndex++;
            }
            mazeColumnIndex = 0;
            mazeRowIndex++;
        }
    }
    
    private ArrayList<Point> getEmptySpots(){
        ArrayList<Point> emptySpots = new ArrayList<Point>();

        for(int i = 0; i < mazeArray.length; i++){
            char[] arrayElements = mazeArray[i];
            for(int j = 0; j < arrayElements.length; j++){
                char element = mazeArray[i][j];
                if(element == ' '){
                    emptySpots.add(new Point(j, i));
                }
            }
        }
        return emptySpots;
    }

    public Point generateEmptyPoint(){
        ArrayList<Point> emptySlots = getEmptySpots();
        int random = (int) ((Math.random() * ( emptySlots.size() - 0 )) + 0);

        return emptySlots.get(random);
    }
}
