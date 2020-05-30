package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

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
        draw();
    }
        
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(10, 10, new TextCharacter('X'));
        screen.refresh();
    }

}
