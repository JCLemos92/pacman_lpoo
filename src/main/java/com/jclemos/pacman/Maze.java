package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int x;
    int y;
    int distanceFromSource;
    ArrayList<Point> previousNodes;

    Node(int x, int y, int dis, ArrayList<Point> previousNodes) {
        this.x = x;
        this.y = y;
        this.distanceFromSource = dis;
        this.previousNodes = previousNodes;
    }
}

public class Maze {

    private static final char WALL_SYMBOL = 'W';
    private static final char EMPTY_WALL_SYMBOL = 'E';
    private NPC pinky;
    private Player pacman;
    private Boolean colision = false;

    

//    private char[][] mazeArray = new char[][]{
//            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
//            {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
//            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
//            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
//            {'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'E', 'E', 'E', 'E', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
//            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'E', 'E', 'E', 'E', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W'},
//            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
//            {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W'},
//            {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
//            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
//    };

        private char[][] mazeArray = new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
                {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'E', 'E', 'W', 'W'},
                {'E', 'E', 'E', 'E', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', ' ', 'W', 'W', ' ', 'W', 'W', 'W'},
                {'E', 'E', 'E', 'E', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', ' ', ' ', 'W'},
                {'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W', ' ', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
                {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W'},
                {'W', ' ', 'W', 'W', 'W', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
    };

    public Maze() {
        pinky = new NPC(14, 1, DynamicEntity.Direction.Up, 2, 200);
        pacman = new Player(1, 1, DynamicEntity.Direction.Down, 1, 3);

        Point pinkyStartDestiny = generateEmptyPoint();
        ArrayList<Point> pinkyWalkingPoints = pathExists(new Point(pinky.getX(), pinky.getY()), new Point(16, 1));
        pinky.setWalkingPoints(pinkyWalkingPoints);
    }

    public boolean mazeCollision(int x, int y){
        if(mazeArray[y][x] != ' '){
            return true;
        } else {
            return false;
        }
    }

    public void draw(TextGraphics graphics, Screen screen){
        if(colision == true){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(17, 17), ' ');

            screen.setCharacter(5, 5, new TextCharacter('Y'));
            screen.setCharacter(6, 5, new TextCharacter('O'));
            screen.setCharacter(7, 5, new TextCharacter('U'));
            screen.setCharacter(9, 5, new TextCharacter('W'));
            screen.setCharacter(10, 5, new TextCharacter('I'));
            screen.setCharacter(11, 5, new TextCharacter('N'));
        } else {
            int mazeRowIndex = 0;
            int mazeColumnIndex = 0;

            for (char[] elementArray : mazeArray) {
                for (char element : elementArray) {
                    if (element == 'W') {
                        graphics.setBackgroundColor(TextColor.Factory.fromString("#1c75bb"));
                        graphics.fillRectangle(new TerminalPosition(mazeColumnIndex, mazeRowIndex), new TerminalSize(1, 1), ' ');
                    }
                    mazeColumnIndex++;
                }
                mazeColumnIndex = 0;
                mazeRowIndex++;
            }

            pacman.draw(graphics);
            pinky.draw(graphics);
        }
    }

    public char[][] getMazeArray() {
        return mazeArray;
    }

    public void ghostsMovement(){

        if(pinky.emptyWalkingPoints()){
            Point pinkyStartDestiny = generateEmptyPoint();
            pinky.setWalkingPoints(pathExists(new Point(pinky.getX(), pinky.getY()), pinkyStartDestiny));
        }

        pinky.walk();

        if(pinky.x == pacman.x && pinky.y == pacman.y){
            colision = true;
        }
    }

    public ArrayList<Point> getEmptySpots(){
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

    public Player getPacman() {
        return pacman;
    }

    private ArrayList<Point> pathExists(Point initialPoint, Point finalPoint) {
        char[][] matrix = new char[mazeArray.length][];

        for(int i = 0; i < mazeArray.length; i++)
            matrix[i] = mazeArray[i].clone();

        Node source = new Node(initialPoint.getY(), initialPoint.getX(), 0, new ArrayList<Point>());
        Queue<Node> queue = new LinkedList<Node>();

        matrix[initialPoint.getX()][initialPoint.getY()] = 'S';
        matrix[finalPoint.getY()][finalPoint.getX()] = 'D';

        queue.add(source);

        while(!queue.isEmpty()) {
            Node poped = queue.poll();

            if(matrix[poped.x][poped.y] == 'D') {
                return poped.previousNodes;
            }
            else {
                matrix[poped.x][poped.y]='W';

                List<Node> neighbourList = addNeighbours(poped, matrix);
                queue.addAll(neighbourList);
            }
        }
        return null;
    }

    private Point generateEmptyPoint(){
        ArrayList<Point> emptySlots = getEmptySpots();
        int random = (int) ((Math.random() * ( emptySlots.size() - 0 )) + 0);

        return emptySlots.get(random);
    }

    private static List<Node> addNeighbours(Node poped, char[][] matrix) {

        List<Node> list = new LinkedList<Node>();

        if((poped.x-1 > 0 && poped.x-1 < matrix.length) && matrix[poped.x-1][poped.y] != 'W') {
            ArrayList<Point> newPoints = new ArrayList<Point>(poped.previousNodes);

            newPoints.add(new Point(poped.y, poped.x-1));

            list.add(new Node(poped.x-1, poped.y, poped.distanceFromSource+1, newPoints));

        }
        if((poped.x+1 > 0 && poped.x+1 < matrix.length) && matrix[poped.x+1][poped.y] != 'W') {
            ArrayList<Point> newPoints = new ArrayList<Point>(poped.previousNodes);

            newPoints.add(new Point(poped.y, poped.x+1));


            list.add(new Node(poped.x+1, poped.y, poped.distanceFromSource+1, newPoints));
        }
        if((poped.y-1 > 0 && poped.y-1 < matrix.length) && matrix[poped.x][poped.y-1] != 'W') {
            ArrayList<Point> newPoints = new ArrayList<Point>(poped.previousNodes);

            newPoints.add(new Point(poped.y-1, poped.x));


            list.add(new Node(poped.x, poped.y-1, poped.distanceFromSource+1, newPoints));
        }
        if((poped.y+1 > 0 && poped.y+1 < matrix.length) && matrix[poped.x][poped.y+1] != 'W') {
            ArrayList<Point> newPoints = new ArrayList<Point>(poped.previousNodes);

            newPoints.add(new Point(poped.y+1, poped.x));

            list.add(new Node(poped.x, poped.y+1, poped.distanceFromSource+1, newPoints));
        }
        return list;
    }
}

