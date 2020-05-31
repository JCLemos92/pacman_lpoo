package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;

public class NPC extends DynamicEntity{

    enum State{
        Normal,
        Scared
    }

    private int points;
    private State state = State.Normal;
    private ArrayList<Point> walkingPoints;

    public NPC(int x, int y, Direction direction, int speed, int points) {
        super(x, y, direction, speed);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void scared(){
        this.state = State.Scared;
    }

    public void normalize(){
        this.state = State.Normal;
    }

    public void draw(TextGraphics graphics){
        if(this.state == State.Normal){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ed1d28"));
            graphics.fillRectangle(new TerminalPosition(x, y), new TerminalSize(1, 1), ' ');
        }
        else{
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ccff10"));
            graphics.fillRectangle(new TerminalPosition(x, y), new TerminalSize(1, 1), ' ');
        }
    }

    public void setWalkingPoints(ArrayList<Point> walkingPoints) {
        this.walkingPoints = walkingPoints;
    }

    public Boolean emptyWalkingPoints() {

        return walkingPoints.isEmpty();
    }

    public void walk(){
        Point nextPoint = walkingPoints.remove(0);

        int dy = y - nextPoint.getY();
        int dx = x - nextPoint.getX();

        if(dy > 0){
            setY( y - 1 );
        }

        if(dy < 0){
            setY( y + 1 );
        }

        if(dx > 0){
            setX(x - 1);
        }

        if(dx < 0){
            setX(x + 1);
        }
    }
}