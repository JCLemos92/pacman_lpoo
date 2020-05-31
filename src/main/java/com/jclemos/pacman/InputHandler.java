package com.jclemos.pacman;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InputHandler {
    private Player player;
    private Maze maze;

    public InputHandler(Player player, Maze maze) {
        this.player = player;
        this.maze = maze;
    }

    public void initInputHandler(){
        KeyEventDispatcher keyEventDispatcher = e -> {

            if(e.getID() == KeyEvent.KEY_RELEASED )
                return true;

            processKey(e);


            return true;
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

    private void processKey(KeyEvent key){
        switch (key.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if(!maze.mazeCollision(player.getX(), player.getY() + 1)){
                    player.setY(player.getY() + 1);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(!maze.mazeCollision(player.getX() - 1, player.getY())){
                    player.setX(player.getX() - 1);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!maze.mazeCollision(player.getX() + 1, player.getY())){
                    player.setX(player.getX() + 1);
                }
                break;
            case KeyEvent.VK_UP:
                if(!maze.mazeCollision(player.getX(), player.getY() - 1)) {
                    player.setY(player.getY() - 1);
                }
                break;

            default:
                return;
        }
    }
}
