package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextGraphicsWriter;
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

import static java.awt.Color.white;
import static java.lang.Thread.sleep;

public class Game {
    private Terminal terminal;
    private Screen screen;

    private int x = 10;
    private int y = 10;
    private TextGraphics graphics;
    Player pacman = new Player(2, 2, DynamicEntity.Direction.Down, 1, 3);
    Wall wall = new Wall(1, 1);
    Maze maze = new Maze();


    public Game() throws IOException {
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(59, 17)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        graphics = screen.newTextGraphics();
    }

    public void run() throws IOException, InterruptedException {
        KeyEventDispatcher keyEventDispatcher = e -> {

            if(e.getID() == KeyEvent.KEY_RELEASED )
                return true;

            processKey(e);


            return true;
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        while(true){
            sleep(60);
            draw();
        }
    }
        
    private void draw() throws IOException {
        screen.clear();
        maze.draw(graphics);
        screen.refresh();
    }

    public void processKey(KeyEvent key){
        switch (key.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                y += 1;
                break;
            case KeyEvent.VK_LEFT:
                x -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                x += 1;
                break;
            case KeyEvent.VK_UP:
                y -= 1;
                break;

            default:
                return;
        }
    }
}
