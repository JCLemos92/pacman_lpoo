package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import static java.lang.Thread.sleep;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private TextGraphics graphics;
    private InputHandler inputHandler;
    private Maze maze;

    public Game() throws IOException {
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(17, 17)).createTerminal();
        screen = new TerminalScreen(terminal);
        maze = new Maze();


        inputHandler = new InputHandler(maze);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        graphics = screen.newTextGraphics();
        inputHandler.initInputHandler();
    }

    public void run() throws IOException, InterruptedException {
        while(true){
            sleep(120);
            logic();
            draw();
        }
    }

    private void logic(){
        maze.ghostsMovement();
    }
        
    private void draw() throws IOException {
        screen.clear();
        maze.draw(graphics, screen);
        screen.refresh();
    }
}
