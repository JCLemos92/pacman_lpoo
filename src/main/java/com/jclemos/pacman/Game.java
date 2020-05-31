package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Game {
    private Terminal terminal;
    private Screen screen;

    private int x = 10;
    private int y = 10;


    public Game() throws IOException {
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(59, 17)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void run() throws IOException {


        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {

              System.out.println(e);
              return true;
            }
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }
        
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, new TextCharacter('X'));
        screen.refresh();
    }

    private void processKey(KeyStroke key){
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowRight){
            x += 1;
        }
        if (key.getKeyType() == KeyType.ArrowLeft){
            x -= 1;
        }
        if (key.getKeyType() == KeyType.ArrowUp){
            y -= 1;
        }
        if (key.getKeyType() == KeyType.ArrowDown){
            y += 1;
        }
    }
}
