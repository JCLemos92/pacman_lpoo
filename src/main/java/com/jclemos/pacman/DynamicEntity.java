package com.jclemos.pacman;

public class DynamicEntity extends MapEntity{

    enum Direction {
        Up,
        Down,
        Left,
        Right
    }

    private int speed;
    private Direction direction;

    public DynamicEntity(int x, int y, Direction direction, int speed) {
        super(x, y);
        direction = direction;
        speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
