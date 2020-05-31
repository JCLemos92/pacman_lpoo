package com.jclemos.pacman;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InputHandler {
    Player player;

    public InputHandler(Player player) {
        this.player = player;
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
                player.setY(player.getY() + 1);
                break;
            case KeyEvent.VK_LEFT:
                player.setX(player.getX() - 1);
                break;
            case KeyEvent.VK_RIGHT:
                player.setX(player.getX() + 1);
                break;
            case KeyEvent.VK_UP:
                player.setY(player.getY() - 1);
                break;

            default:
                return;
        }
    }
}
